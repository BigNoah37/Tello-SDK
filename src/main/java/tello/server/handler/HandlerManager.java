package tello.server.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import tello.modes.ModeManger;
import tello.server.constant.ServerConstant;
import tello.server.handler.ServerResourceHandler.Handler404;

public class HandlerManager implements HttpHandler {
    private static final Logger LOGGER = Logger.getLogger(HandlerManager.class.getName());

    private Map<String, Handler> handlers = new HashMap<String, Handler>();

    private Handler404 handler404;

    private ModeManger modeManger;

    public HandlerManager(Handler404 handler404) {
        this.handler404 = handler404;
        modeManger = new ModeManger();

        //modeManger.addMode();
    }

    public void addHandler(String route, Handler handler) {
        handlers.put(route, handler);
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String path =  getPath( he );

        LOGGER.info("Route Requested With HandlerManager: "  + path);

        if (handlers.containsKey(path)) {
            Handler handle = handlers.get(path);

            if ( handle.isMethod( getMethod(he) ) ){ // Can not give handler a method that it isn't allowed to handle
                handle.handle(he);
            }else {
                handler404.server404(he, ServerConstant.Error404File);
            }
        }else {
            handler404.server404(he, ServerConstant.Error404File);
        }
    }

    public String getPath(HttpExchange he) {
        return he.getRequestURI().getPath();
    }

    public String getMethod(HttpExchange he) {
        return he.getRequestMethod();
    }
}