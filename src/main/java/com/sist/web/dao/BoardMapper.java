package com.sist.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.web.vo.BoardVO;

@Mapper
public interface BoardMapper {
		@Select("""
			    SELECT no, name, subject, TO_CHAR(regdate, 'YYYY-MM-DD') AS regdate, hit
			    FROM (
			        SELECT no, name, subject, regdate, hit, ROWNUM AS rn
			        FROM (
			            SELECT * FROM yhw_board ORDER BY no DESC
			        )
			        WHERE ROWNUM <= #{end}
			    )
			    WHERE rn >= #{start}
				""")
		public List<BoardVO> boardListData(@Param("start") int start, @Param("end") int end);
		
		@Select("SELECT COUNT(*) FROM yhw_board")
	    public int count();

		@Select("""
			    SELECT no, name, subject, content, pwd, TO_CHAR(regdate, 'YYYY-MM-DD') AS regdate, hit
			    FROM yhw_board
			    WHERE no = #{no}
			""")
	    public BoardVO findByNo(@Param("no") int no);

	    @Update("UPDATE yhw_board SET hit = hit + 1 WHERE no = #{no}")
	    public void hitIncrement(@Param("no") int no);

	    @Insert("""
	        INSERT INTO yhw_board(no, name, subject, content, pwd, regdate, hit)
	        VALUES((SELECT NVL(MAX(no),0)+1 FROM yhw_board),
	               #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0)
	    """)
	    public void boardInsert(BoardVO vo);

	    @Update("""
	        UPDATE yhw_board
	        SET name = #{name}, subject = #{subject}, content = #{content}
	        WHERE no = #{no}
	    """)
	    public void boardUpdate(BoardVO vo);

	    @Delete("DELETE FROM yhw_board WHERE no = #{no}")
	    public void boardDelete(@Param("no") int no);
}
