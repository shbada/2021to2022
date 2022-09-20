package me.whiteship.refactoring._22_data_class._42_encapsulate_record.done;

// 불변 객체
// 자바에서 제공하는 record 기능
// 해당 값을 변경할 수 없다. get만 가능
public record OrganizationRecord(String name, String country) {

}
