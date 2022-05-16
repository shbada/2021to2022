package com.spring.batch._69_skip;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class SkipItemWriter implements ItemWriter<String> {

    private int cnt = 0;

    @Override
    public void write(List<? extends String> items) throws Exception {
        /*
          -12 예외 발생 - skip
          다시 처음으로 간다 (ItemReader 1 - 근데 이때는 다시 읽는게 아니고(read()실행이 아님) 이미 chunk 에 저장된 데이터를 가져온다)
          받으면 다시 ItemWriter 로 와서 if문 타고, 여기선 skip cnt 초과이므로 실제 에러 발생

          cnt 를 4로 설정하고 하면

          -12일때 예외 발생 - skip
          그리고 여기서부터 12, 13, 14, 15, 16은 다시 process 에서 데이터를 하나씩 가져와서 수행
          그래서 로그를 보면
          process 13
          writer -13
          process 14
          writer -14

          이런식으로 itemProcess 한건씩 다시 수행한다.

          그 이후로는 다시 정상적으로 chunk 만큼 수행
         */
        for (String item : items) {
            if(item.equals("-12")) {
                System.out.println("ItemWriter : " + item);
                cnt++;
                throw new SkippableException("Write failed. cnt:" + cnt);
            }
            else {
                System.out.println("ItemWriter : " + item);
            }
        }
    }
}
