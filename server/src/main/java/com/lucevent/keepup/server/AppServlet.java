package com.lucevent.keepup.server;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.lucevent.keepup.data.util.LeagueTable;
import com.lucevent.keepup.data.util.Section;
import com.lucevent.keepup.data.util.Sport;
import com.lucevent.keepup.server.utils.BackendParser;
import com.lucevent.keepup.server.utils.Statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        if (req.getParameter("sports") != null) {

            String[] params = req.getParameter("code").split(",");

            Sport sport = Data.sports.getSportByCode(Integer.parseInt(params[0]));
            Section section = sport.sections.get(Integer.parseInt(params[1]));

            LeagueTable content = sport.getLeagueTable(section);

            resp.getWriter().println(BackendParser.toEntry(content));

        } else if (req.getParameter("clear") != null) {


        }

    }

    @Override
    public void destroy()
    {
        super.destroy();
        Data.stats.save();
    }

    @Override
    public void init() throws ServletException
    {
        super.init();

        ObjectifyFactory oFactory = ObjectifyService.factory();
        oFactory.register(Statistics.class);
        oFactory.begin();

        new Data();
    }

}
