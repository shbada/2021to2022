package com.spring.batch._41_itemStream.custom;

import org.springframework.batch.item.*;

import java.util.List;

public class CustomItemStreamReader implements ItemStreamReader<String> {
    private final List<String> items;
    private int index = -1; // 아이템을 읽다가 실패했을 경우, 그때의 실패 시점의 데이터를 DB에 저장하는데, index 추적 가능하도록 저장
    private boolean restart = false; // 재시작 여부

    public CustomItemStreamReader(List<String> items) {
        this.items = items;
        this.index = 0;
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        String item = null;

        if (this.index < this.items.size()) {
            item = this.items.get(index);
            index++; // index 1씩 증가
        }

        /*
         * 실패
         * 잡 재시작 가능 (Job이 실패로 끝났기 때문)
         * 재시작 하면 Reader 의 open()에서 index를 셋팅할 수 있다.
         * 실패하기 직전까지의 index가 저장되므로 6에 실패했을때 5가 저장된다. (다음 배치는 5부터 시작)
         * update() 메서드는 chunk size 만큼의 1 cycle 완료시마다 실행된다.
         */
        if (this.index == 6 && !restart) { // 고의 실패 (재시작이 아닐 경우에만)
            throw new RuntimeException("Restart is required");
        }

        return item;
    }

    /**
     * 초기화작업
     * @param executionContext
     * @throws ItemStreamException
     */
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        // ExecutionContext 의 map 에 데이터 저장이 가능하다.
        if (executionContext.containsKey("index")) { // index 키 데이터가 DB에 저장되어있다는 얘기
            index = executionContext.getInt("index");
            this.restart = true; // 재시작 가능으로 변경
        } else {
            index = 0;
            executionContext.put("index", index);
        }
    }

    /**
     * chunk size 만큼의 1 cycle 완료시마다 실행된다.
     * @param executionContext
     * @throws ItemStreamException
     */
    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // 잡이 재시작될때 여기서 저장된 마지막 index 를 가지고온다.
        executionContext.put("index", index);
    }

    /**
     * 리소스 해제 또는 초기화작업 해제 등 마무리 작업
     * @throws ItemStreamException
     */
    @Override
    public void close() throws ItemStreamException {
        System.out.println("close");
    }
}
