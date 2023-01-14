package com.book.jpa.chapter06.F다대다_한계극복;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
public class MemberProductId implements Serializable {
    private Long memberF;
    private Long productF;
}
