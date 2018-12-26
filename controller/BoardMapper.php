<?php
class BoardMapper {
    /**
     * 글 리스트
     */
    public function selectBoardList() {
        return $query = "SELECT
                            b.idx,
                            b.title AS title, 
                            b.content,
                            b.view_cnt AS viewCnt,
                            b.created_date AS createdDate,
                            u.user_name AS userName
                        FROM
                          board_notice b JOIN users u ON b.created_user_idx = u.idx
                        WHERE b.is_deleted = 'N'
                        ORDER BY b.idx DESC";
    }

    /**
     * 글 상세조회
     */
    public function selectBoardInfo() {
        return $query = "SELECT
                            b.idx,
                            b.title AS title,
                            b.content,
                            b.view_cnt AS viewCnt,
                            b.created_date AS createdDate,
                            u.user_name AS userName
                        FROM board_notice b JOIN users u ON b.created_user_idx = u.idx
                        WHERE
                              b.idx = :idx
                          AND
                              is_deleted = 'N'";
    }

    /**
     * 글 등록
     */
    public function addBoard() {
        return $query = "INSERT INTO board_notice( title, content, created_user_idx ) VALUE ( :title, :content, :created_user_idx )";
    }

    /**
     * 글 수정
     */
    public function updateBoard() {
        return $query = "UPDATE board_notice
                        SET
                            title = :title,
                            content = :content,
                            updated_user_idx = :updated_user_idx,
                            updated_date = NOW()
                        WHERE
                            idx = :idx
                          AND
                            is_deleted = 'N'";
    }

}
?>