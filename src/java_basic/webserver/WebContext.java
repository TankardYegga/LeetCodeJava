package java_basic.webserver;

import java.util.*;

/**
 * Description:
 * Creator: levin
 * Date: 10/25/2022
 * Time: 6:10 PM
 * Email: levinforward@163.com
 */
public class WebContext {

    private List<ServerEntity> serverEntities;
    private List<Mapping> mappings;

    private Map<String, String> serverEntityMap = new HashMap<String, String>();
    private Map<String, String> mapMap = new HashMap<String, String>();

    public WebContext(List<ServerEntity> serverEntities, List<Mapping> mappings){
        super();
        this.serverEntities = serverEntities;
        this.mappings = mappings;

        for(ServerEntity serverEntity: serverEntities){
            serverEntityMap.put(serverEntity.getName(), serverEntity.getClassPath());
        }

        for(Mapping map: mappings){
            Set<String> ulrPatterns = map.getUrlPatterns();
            for(String ulrPattern: ulrPatterns){
                mapMap.put(ulrPattern, map.getName());
            }
        }
    }

    public String getCls(String urlPattern){
        //一个servel-class对应多个url-pattern
        //但是一个url-pattern只对应一个server-class

        return serverEntityMap.get(mapMap.get(urlPattern));
    }

}
