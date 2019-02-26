package com.mi.chat.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.mi.chat.model.dao.ChatDao;
import com.mi.chat.model.vo.Chat;
import com.mi.chat.model.vo.Chatroom;

public class ChatService {
	
	private ChatDao dao = new ChatDao();

	public List<Chatroom> selectAllChatroom(String memberId) {
		Connection conn = getConnection();
		List<Chatroom> list = dao.selectAllChatroom(conn, memberId);
		close(conn);
		return list;
	}
	
	public List<Chat> selectAllChat(int chatroomId) {
		Connection conn = getConnection();
		List<Chat> list = dao.selectAllChat(conn, chatroomId);
		close(conn);
		return list;
	}
	
	public int insertChat(Chat c) {
		Connection conn = getConnection();
		int result = dao.insertChat(conn, c);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
}