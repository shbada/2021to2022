let sql = require('../utils/db');

let boardMapper = {
    /** 리스트 조회 */
    selectBoardList: function() {
        return sql.promiseQuery("SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board WHERE is_deleted = 'N' ORDER BY idx");
    },

    /** 글 단건조회 */
    selectBoard: function(idx) {
        return sql.promiseQuery("SELECT idx, title, content, view_cnt AS viewCnt, like_cnt AS likeCnt, writer_idx AS writerIdx, created_date AS createdDate FROM free_board WHERE idx = ? AND is_deleted = 'N'", [idx])
    },

    /** 글 등록 */
    insertBoard: function(title, content, idx) {
        sql.promiseQuery("INSERT INTO free_board(title, content, writer_idx) VALUES(?, ?, ?)", [title, content, idx]);
    },

    /** 글 수정 */
    updateBoard: function (title, content, idx) {
        sql.promiseQuery("UPDATE free_board SET title = ?, content = ?, updated_date = NOW() WHERE idx = ?", [title, content, idx])
    },

    /** 글 삭제 */
    deleteBoard: function (idx) {
        sql.promiseQuery("UPDATE free_board SET is_deleted = 'Y' WHERE idx = ?", [idx])
    }
}

module.exports = boardMapper;
