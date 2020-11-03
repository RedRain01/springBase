package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.serviceImpl.MongoServerImpl;
import com.example.demo.util.BaseService;
import com.example.demo.util.Pager;
import com.example.demo.util.QueryBean;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Test {


    @Autowired
    @Qualifier(value = "primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;
    @Autowired
    MongoServerImpl mongoServerImpl;


    @RequestMapping(value="/aa",method = RequestMethod.GET)
    public String aa(){
        try
        {
            Pager pager = new Pager();
            pager.setFlag(0);
            pager.setPageSize(10);
            List<QueryBean> listQuery = new ArrayList<QueryBean>();
            listQuery.add(new QueryBean("CUSTID","=","2012102200001925"));
            String json = listQuery.toString();
            JSONArray jsonArray = JSON.parseArray(json);
            Map<String, Object> custByJson =mongoServerImpl.findCustByJson(jsonArray, pager, "CUST_INFO");
            return "========="+JSON.toJSONString(custByJson);
        }
        catch (Exception e)
        {
            return "----";
        }
    }

    @RequestMapping(value="/cc",method = RequestMethod.GET)
    public String list14(){
        try
        {
            Pager pager = new Pager();
            pager.setFlag(0);
            pager.setPageSize(10);
            List<QueryBean> listQuery = new ArrayList<QueryBean>();
            listQuery.add(new QueryBean("CUSTID","=","2012102200001925"));
            String json = listQuery.toString();
            JSONArray jsonArray = JSON.parseArray(json);
            Map<String, Object> custByJson = findCustByJson(jsonArray, pager, "CUST_INFO");
            return "========="+JSON.toJSONString(custByJson);
       }
        catch (Exception e)
        {
            return "----";
        }
    }

    public Map<String, Object> findCustByJson(JSONArray jsonArray, Pager pager, String tableName) {
        Map<String, Object> resultMap=new HashMap<>();
        try {
            Query query = getQuery(jsonArray, pager, tableName);
            if (query == null) {
                resultMap.put("state","error");
                resultMap.put("msg","basic查询条件query为空");
                return  resultMap;
            }
            List<Document> documentList = primaryMongoTemplate.find(query, Document.class,tableName);
            resultMap.put("state","success");
            resultMap.put("data",JSONArray.parseArray(JSON.toJSONString(documentList)));
            return  resultMap;
        }catch (Exception e)
        {
            resultMap.put("state","error");
            resultMap.put("msg",e.getMessage());
            return  resultMap;
        }
    }

    /**
     * Title: getQuery
     * Date: 2019年9月23日 上午8:40:20
     * Description:获取查询器
     * @param array
     * @param pager
     * @param tableName
     * @return
     */
    public Query getQuery(JSONArray array, Pager pager, String tableName) {
        BaseService baseService=new BaseService();
        Map<String, Object> map = baseService.toCurd(array, pager);
        Query query = (Query) map.get("query");
        return query;
    }

}
