import javax.servlet.*;
import java.io.IOException;

public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("[INFO] MyServlet,收到一个servlet请求");
        String remoteAddr = servletRequest.getRemoteAddr();
        servletResponse.getWriter().print(remoteAddr);

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
