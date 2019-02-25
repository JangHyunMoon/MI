package com.mi.group.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.mi.group.model.dao.GroupDao;
import com.mi.group.model.vo.Group;

public class GroupService {
	private GroupDao dao=new GroupDao();
	
	public List<Group> selectAllGroup(String memberId){
		Connection conn=getConnection();
		
		List<Group> groupList=dao.selectAllGroup(conn,memberId);
		close(conn);
		return groupList;
	}
	
	/* public List<Group> groupMember(String ) */
	
	public List<String> selectId(String search)
	{
		Connection conn=getConnection();
		List<String> list=dao.selectId(conn,search);
		close(conn);
		return list;
		
	}
	public int addGroup(String gName, String[] members){
		for(String s : members) {
			System.out.println(s);
		}
		Connection conn=getConnection();
		int result=dao.addGroup(conn, gName, members);
		close(conn);
		return result;
	}
	
	public int addGroupMember(String gName, String[] members)
	{
		Connection conn=getConnection();
		int result=dao.addGroupMember(conn, gName, members);
		close(conn);
		return result;
	}
	
	public String selectGroupId(String groupName) {
	      Connection conn=getConnection();
	      String groupId=dao.selectGroupId(conn,groupName);
	      close(conn);
	      return groupId;
	   }
}
