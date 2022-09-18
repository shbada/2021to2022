package com.expedia.b2b.acceptance.accommodation;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public final class AccommodationParams {
    public static Map<String, Object> 숙박업체_정보_셋팅() {
        Map<String, Object> params = new HashMap<>();
        params.put("accommodationName", "Shilla Stay Mapo");
        params.put("accommodationType", 10);
        params.put("bizno", "1235679466");
        params.put("countryCd", "KR");
        params.put("city", "Seoul");
        params.put("state", "Mapo-gu");
        params.put("street", "Mapo-daero 83");
        params.put("zipcode", "04156");
        params.put("checkinDescription", "Check-in is from 03:00 PM, and check-out is until 11:00 PM");
        params.put("checkinStartTime", LocalTime.of(15, 0, 0));
        params.put("checkoutEndTime", LocalTime.of(23, 0, 0));
        params.put("minCheckinAge", 20);
        params.put("respeUserId", "test");
        params.put("respeEmail", "test@naver.com");
        params.put("respeHpno", "01012341234");
        params.put("telNo", "0212341234");
        params.put("lastModifiedBy", "test");
        params.put("createdBy", "test");

        return params;
    }

    public static Map<String, Object> 숙박업체_권한_정보_셋팅() {
        Map<String, Object> params = new HashMap<>();
        params.put("progStatusCd", 10);
        params.put("stoppedDts", "");
        params.put("stoppedRsn", "");
        params.put("lastModifiedBy", "test");
        params.put("createdBy", "test");

        return params;
    }

    public static Map<String, Object> 객실_정보_셋팅() {
        Map<String, Object> params = new HashMap<>();
        params.put("roomType", "VIP");
        params.put("roomName", "VIP룸");
        params.put("maxEntranceCnt", 4);
        params.put("lastModifiedBy", "test");
        params.put("createdBy", "test");

        return params;
    }

    public static Map<String, Object> 객실_부가정보_셋팅() {
        Map<String, Object> params = new HashMap<>();
        params.put("codeId", "A001-01");
        params.put("active", 1);
        params.put("lastModifiedBy", "test");
        params.put("createdBy", "test");

        return params;
    }

    public static Map<String, Object> 객실_요금_셋팅() {
        Map<String, Object> params = new HashMap<>();
        params.put("active", 1);
        params.put("feeName", "VIP룸 기본요금");
        params.put("fee", 100000);
        params.put("lastModifiedBy", "test");
        params.put("createdBy", "test");

        return params;
    }
}
