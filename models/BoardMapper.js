let sql = require('../utils/dbConnection');

let boardMapper = {
    /** 리스트 조회 */
    selectBoardList: function() {
        return sql.query("SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board ORDER BY idx");
    },

    /** 글 단건조회 */
    selectBoard: function(idx) {
        return sql.query("SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board WHERE idx = ?", [idx])
    },

    /** 글 등록 */
    insertBoard: function(params) {
        sql.query("INSERT INTO free_board(title, content, writer_idx) VALUES(?, ?, ?)", [params]);
    },

    /** 글 수정 */
    updateBoard: function (params) {
        sql.query("UPDATE free_board SET title = ?, content = ?, updated_date = NOW() WHERE idx = ?", [params])
    },

    /** 글 삭제 */
    deleteBoard: function (idx) {
        sql.query("DELETE FROM free_board WHERE idx = ?", [idx])
    }
}

module.exports = boardMapper;
