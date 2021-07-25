import { Entity, BaseEntity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, OneToMany } from "typeorm";
import Message from "./Message";

@Entity() // 이 밑에는 Entity 내용이라는 말
class Channel extends BaseEntity {
    @PrimaryGeneratedColumn() // DB에서 AutoIncrement 의미 (PK)
    id: number;

    @Column({type: "text", nullable: false})
    channelName: string;
    
    // 각 Entity에 사용하고 있음을 알려야한다.
    // Channel 입장에서는 Channel : Message = 1 : N
    @OneToMany(
        type => Message,
        message => message.innerChannel
    )
    messages: Message[]

    @CreateDateColumn() // 데이터가 생성된 날짜가 자동으로 입력
    createdAt: string
    
    @UpdateDateColumn() // 데이터가 수정된 날짜가 자동으로 입력
    updatedAt: string
}

export default Channel;