package com.mi.calendar.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.mi.event.model.service.EventService;
import com.mi.event.model.vo.Event;
import com.mi.group.model.service.GroupService;
import com.mi.group.model.vo.Group;

/**
 * Servlet implementation class CalendarGroupAjaxServlet
 */
@WebServlet("/calendar/groupAjax.do")
public class CalendarGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId=request.getParameter("memberId");
		String groupId=request.getParameter("groupId");
		System.out.println(memberId+" : "+groupId);
		List<Event> eventList=new EventService().selectGroupEvent(memberId,groupId);
		List<Group> groupList=new GroupService().selectAllGroup(memberId);
		System.out.println(eventList);
		Date today=new Date();
		SimpleDateFormat defaultDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String defaultToday="";
		
		try {
			defaultToday=defaultDateFormat.format(today);
		}catch(Exception e) {e.printStackTrace();}
		
		JSONArray eventJArr=new JSONArray();
		for(Event e:eventList) {
			JSONObject jo=new JSONObject();
			jo.put("groupId", e.getGroupId());
			jo.put("title", e.getTitle());
			jo.put("startDate", e.getStartDate());
			jo.put("prepairingId", e.getPrepairingId());
			jo.put("eventId", e.getEventId());
			jo.put("filePath", e.getFilePath());
			jo.put("memo", e.getMemo());
			jo.put("endDate", e.getEndDate());
			eventJArr.add(jo);
		}
			
		JSONArray groupJArr=new JSONArray();
		for(Group g:groupList) {
			JSONObject jo=new JSONObject();
			jo.put("groupId", g.getGroupId());
			jo.put("groupCaptin", g.getGroupCaptin());
			jo.put("groupName", g.getGroupName());
			
			groupJArr.add(jo);
		}
		
		response.setContentType("application/json;charset=UTF-8");
		
		JSONObject json=new JSONObject();

		json.put("defaultToday", defaultToday);
		json.put("memberId", memberId);
		json.put("eventJArr", eventJArr);
		json.put("groupJArr", groupJArr);
		
		new Gson().toJson(json,response.getWriter());	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
