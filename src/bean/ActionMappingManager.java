package bean;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 加载配置文件, 封装所有的整个 mystruts.xml
 * Created by Administrator on 2017/1/6.
 */
public class ActionMappingManager {

    //保存action的集合
    private Map<String, ActionMapping> allActions;

    public ActionMappingManager() {
        allActions = new HashMap<String, ActionMapping>();
        this.init();
    }

    public ActionMapping getActionMapping(String actionName){
        if (actionName == null) {
            throw new RuntimeException("传入参数有误，请查看struts.xml配置的路径。");
        }

        ActionMapping actionMapping = allActions.get(actionName);
        if (actionMapping == null) {
            throw new RuntimeException("该路径在struts.xml中找不到，请检查");
        }
        return actionMapping;
    }

    //初始化allCollection集合
    private void init() {
        //DOM4J读取配置文件
        try {
            //1.得到解析器
            SAXReader reader = new SAXReader();
            //得到mystruct.xml文件流
            InputStream in = this.getClass().getResourceAsStream("/mystructs.xml");

            //2.加载文件
            Document doc = reader.read(in);

            //3，获取根节点
            Element root = doc.getRootElement();

            //4.得到packet节点
            Element ele_package = root.element("package");

            //5.得到根结点下所有的action节点
            List<Element> listAction = ele_package.elements("action");

            //6.遍历，封装
            for (Element e : listAction) {
                // 6.1 封装一个ActionMapping对象
                ActionMapping actionMapping = new ActionMapping();
                actionMapping.setName(e.attributeValue("name"));
                actionMapping.setClassName(e.attributeValue("class"));
                actionMapping.setMethod(e.attributeValue("method"));

                // 6.2 封装当前aciton节点下所有的结果视图
                Map<String,Result> results = new HashMap<String,Result>();

                //得到action节点下的所有result节点
                Iterator<Element> it = e.elementIterator("result");
                while(it.hasNext()){
                    Element ele_result = it.next();

                    //封装对象
                    Result res = new Result();
                    res.setName(ele_result.attributeValue("name"));
                    res.setType(ele_result.attributeValue("type"));
                    res.setPage(ele_result.getTextTrim());
                    //添加到集合
                    results.put(res.getName(),res);
                }
                // 设置到actionMapping中
                actionMapping.setResults(results);

                //actionMapping添加到map集合
                allActions.put(actionMapping.getName(),actionMapping);
            }

        } catch (Exception e) {
            throw new RuntimeException("启动初始化出错",e);
        }
    }
}