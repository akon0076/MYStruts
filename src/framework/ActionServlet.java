package framework;

import bean.ActionMapping;
import bean.ActionMappingManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionMappingManager actionMappingManager = new ActionMappingManager();
        String actionName = request.getRequestURI().replace("/", "").replace(".action", "");
        ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
        try {
            Class<?> action = Class.forName(actionMapping.getClassName());
            Object objAction = (Object) action.newInstance();
            Object result = objAction.getClass().getDeclaredMethod(actionMapping.getMethod(), HttpServletRequest.class, HttpServletResponse.class).invoke(objAction, request, response);
            if(true)
                System.out.println("ddd");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
