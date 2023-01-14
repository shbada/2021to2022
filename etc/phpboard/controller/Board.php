<?php
require_once "Database.php";

class Board {
    /** db 연동 */
    private $pdo;
    private $boardMapper;

    public function __construct() {
        $database = new Database();
        $this->pdo = $database->pdo;
    }

    /**
     * 글 리스트
     * @return array
     */
    public function selectBoardList() {
        $query = "SELECT
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

        $stmt = $this->pdo->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

        return $result;
    }

    /**
     * 글 상세조회
     * @return mixed
     */
    public function selectBoardInfo($idx) {
        $query = "SELECT
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

        $stmt = $this->pdo->prepare($query);
        $stmt->bindParam(':idx', $idx, PDO::PARAM_INT);

        $stmt->execute();
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        return $result;
    }

    /**
     * 글 등록
     */
    public function addBoard($title, $content) {
        try {
            $query = "INSERT INTO board_notice( title, content, created_user_idx ) VALUE ( :title, :content, :created_user_idx )";
            $createdUserIdx = 1;

            $stmt = $this->pdo->prepare($query);
            $stmt->bindParam(':title', $title, PDO::PARAM_STR);
            $stmt->bindParam(':content', $content, PDO::PARAM_STR);
            $stmt->bindParam(':created_user_idx', $createdUserIdx, PDO::PARAM_INT);

            $stmt->execute();

            if($stmt)
                echo "success";
            else
                echo "false";
        } catch (PDOException $e) {
            $this->pdo->rollBack();
            throw $e;
        }
    }

    /**
     * 글 수정
     */
    public function updateBoard($title, $content, $idx) {
        $query = "UPDATE board_notice
                        SET
                            title = :title,
                            content = :content,
                            updated_user_idx = :updated_user_idx,
                            updated_date = NOW()
                        WHERE
                            idx = :idx
                          AND
                            is_deleted = 'N'";
        $updatedUserIdx = 1;

        try {
            $stmt = $this->pdo->prepare($query);
            $stmt->bindParam(':title', $title, PDO::PARAM_STR);
            $stmt->bindParam(':content', $content, PDO::PARAM_STR);
            $stmt->bindParam(':updated_user_idx', $updatedUserIdx, PDO::PARAM_INT);
            $stmt->bindParam(':idx', $idx, PDO::PARAM_INT);

            $stmt->execute();

            if($stmt)
                echo "success";
            else
                echo "false";
        } catch (PDOException $e) {
            throw $e;
        }
    }

}
?>