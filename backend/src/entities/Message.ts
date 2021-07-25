import { Entity, BaseEntity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, ManyToOne } from "typeorm";
import Channel from "./Channel";

@Entity()
class Message extends BaseEntity {
    @PrimaryGeneratedColumn()
    id: number;

    @Column({type: "text", nullable: false})
    nickname: string;

    @Column({ type: "text", nullable: false })
    thumbnail: string;
    
    @Column({type: "text", nullable: false})
    contents: string;

    // Message 입장에서는 Message : Channel = N : 1
    @ManyToOne(
        type => Channel,
        channel => channel.messages
    )
    innerChannel: Channel

    @Column({type: "text", nullable: false})
    innerChannelId: number;
    
    @CreateDateColumn()
    createdAt: string;

    @UpdateDateColumn()
    updatedAt: string;
}

export default Message;