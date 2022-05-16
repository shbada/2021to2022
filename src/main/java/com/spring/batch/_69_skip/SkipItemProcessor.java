package com.spring.batch._69_skip;

import org.springframework.batch.item.ItemProcessor;

public class SkipItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;

    @Override
    public String process(String item) throws Exception {

        /*
          6일때 에러 발생, - skip
          다시 reader 로 가서 데이터를 가져온다. (6을 제외하고)
          1,2,4,5

          7일대 에러 발생 - skip
          이때는 실제 오류 발생 - skip 횟수 2번이 넘었기 때문
         */
        if(item.equals("6") || item.equals("7")) {
            System.out.println("ItemProcessor : " + item);
            cnt++;
            throw new SkippableException("Process failed. cnt:" + cnt);
        }
        else {
            System.out.println("ItemProcessor : " + item);
            return String.valueOf(Integer.valueOf(item) * -1);
        }
    }
}
