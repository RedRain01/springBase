package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import java.io.Serializable;
import java.util.*;

/**
 * @Auther Guanzs
 * @Date 2017/9/13
 * @Desc
 */
public class BaseService implements Serializable{

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    /**
     * 条件查询
     * @param array  json 数组对象
     * @return
     */
    public Map<String,Object> toCurd(JSONArray array, Pager pager){
        long t0 = Calendar.getInstance().getTimeInMillis();
        Map<String,Object> map = new HashMap<String, Object>();
        JSONObject sqlWhere = new JSONObject();
        try {
            Criteria criteria = new Criteria();
            if (array!=null && array.size() > 0){
                Document dbObject = new Document();
                Document basicDBObject = new Document();
                // 不查询_id
                //basicDBObject.put("_id", false);W
                if (pager!= null){
                    if (pager.getQueryFields() != null){
                        Set<String> stringSet = pager.getQueryFields();
                        if (!stringSet.contains("SORT_ID")){
                            stringSet.add("SORT_ID");
                            //System.out.println("没有加入排序值");
                        }
                        for (String key : stringSet){
                            basicDBObject.put(key.toUpperCase(),true);
                        }
                    }
                }

                Criteria like = null;
                //Criteria noLikeTop = null;
                for (int i = 0; i < array.size(); i++){
                    JSONObject object = array.getJSONObject(i);

                    String key = "";
                    if (object.getString("key") != null){
                        key = object.getString("key").toUpperCase();
                    }

                    String op = "";
                    if (object.getString("op") != null){
                        op = object.getString("op");
                    }

                    String val = "";
                    if (object.getString("val") != null){
                        val = object.getString("val");
                    }

                    JSONArray keys =  null;
                    if (object.getJSONArray("keys") != null && object.getJSONArray("keys").size() > 0){
                        keys = object.getJSONArray("keys");
                        for (int k = 0;k<keys.size();k++){
                            keys.set(k,keys.get(k).toString().toUpperCase());
                        }
                    }

                    JSONArray vals =  null;
                    if (object.getJSONArray("vals") != null && object.getJSONArray("vals").size() > 0){
                        vals = object.getJSONArray("vals");
                    }

                    // 日期区间转换条件
                    if (object.containsKey("flag")){
                        if (object.getBoolean("flag")){
                            if (!StringUtils.isEmpty(key)){
                                if (object.getString("startValue") != null && object.getString("endValue") != null){
                                    String  startValue = object.getString("startValue");
                                    String  endValue = object.getString("endValue");
                                    criteria.and(key).gte(startValue).lte(endValue);
                                    if (pager != null){
                                        if (pager.getDataField() != null){
                                            String dateField = pager.getDataField();
                                            if (dateField.equals(key)){
                                                map.put("startValue",startValue);
                                                map.put("endValue",endValue);
                                            }
                                        }
                                    }
                                    sqlWhere.put(key+" >= ","\'"+startValue+"\'");
                                    sqlWhere.put(key+" <= ","\'"+endValue+"\'");
                                   /* if (startValue.equals(endValue)|| (Integer.parseInt(endValue) - Integer.parseInt(startValue) < 2) ){
                                        map.put("flag","true");
                                    }*/
                                }
                            }
                        }
                    }

                    if (object.containsKey("type")){
                        if (object.getString("type").equals("double")){
                            if (!StringUtils.isEmpty(key)){
                                if (object.getString("startValue") != null && object.getString("endValue") != null){
                                    Double  startValue = object.getDoubleValue("startValue");
                                    Double  endValue = object.getDoubleValue("endValue");
                                    criteria.and(key).gte(startValue).lte(endValue);
                                    sqlWhere.put(key+" >= ",startValue);
                                    sqlWhere.put(key+" <= ",endValue);
                                }
                            }
                        }
                        if (object.getString("type").equals("=")){
                            if (!StringUtils.isEmpty(key)){
                                if (object.getString("startValue") != null){
                                    Double  value = object.getDoubleValue("startValue");
                                    criteria.and(key).is(value);
                                    sqlWhere.put(key+" = \'",value+"\'");
                                }
                            }
                        }
                    }

                    // 定义字段和值的数组
                    String[] values = null;
                    String[] fields = null;
                    // 获得数组fields
                    if (vals != null && vals.size() > 0){
                        values = new String[vals.size()];
                        for (int j = 0;j<vals.size();j++){
                            String v = vals.getString(j);
                            values[j] = v;
                        }
                    }
                    // 获得数组values
                    if (keys != null && keys.size() > 0){
                        fields = new String[keys.size()];
                        for (int j = 0;j<keys.size();j++){
                            String v = keys.getString(j);
                            fields[j] = v;
                        }
                    }

                    if (op.equals(">")){
                        criteria.and(key).gt(val);
                        sqlWhere.put(key+" > ","\'"+val+"\'");
                    }
                    if (op.equals("<")){
                        criteria.lt(key).lt(val);
                        sqlWhere.put(key+" < ","\'"+val+"\'");
                    }
                    if (op.equals(">=")){
                        criteria.and(key).gte(val);
                        sqlWhere.put(key+" >= ","\'"+val+"\'");
                    }
                    if (op.equals("<=")){
                        criteria.and(key).lte(val);
                        sqlWhere.put(key+" <= ","\'"+val+"\'");
                    }
                    if (op.equals("=")){
                        if (key.equals("_ID")){
                            criteria.and("_id").is(new ObjectId(val));
                        }else {
                            criteria.and(key).is(val);
                            sqlWhere.put(key+" = ","\'"+val+"\'");
                        }

                    }
                    if (op.equals("ne")){
                        criteria.and(key).ne(val);
                        sqlWhere.put(key+" != ","\'"+val+"\'");
                    }
                    if (op.equals("neMul")){
                        if (values != null){
                            for (String value : values){
                                sqlWhere.put(key+" != ","\'"+value+"\'");
                            }
                        }
                    }
                    // 以某个字符开头匹配
                    if(op.equals("likeTop")){
                        criteria.and(key).regex("^"+val);
                        sqlWhere.put(key+" like ","\'"+val+"%\'");
                    }
                    // 包含字符匹配
                    if (op.equals("likeInclude")){
                        criteria.and(key).regex(val);
                        sqlWhere.put(key+" like ","\'%"+val+"%\'");
                    }
                    // 以某个字符结尾匹配
                    if (op.equals("likeEnd")){
                        criteria.and(key).regex(val+"$");
                        sqlWhere.put(key+" like ","\'%"+val+"\'");
                    }
                    if (op.equals("noLikeTop")){
                        criteria.and(key).not().regex("^"+val);
                    }
                    if (op.equals("noLikeInclude")){
                        criteria.and(key).not().regex(val);
                    }
                    if (op.equals("noLikeEnd")){
                        criteria.and(key).not().regex(val+"$");
                    }
                    if (op.equals("noLikeTopMul")){
                        Criteria[] noLike = new Criteria[values.length];
                        if (values != null && values.length >0){
                            for (int k = 0; k<vals.size();k++){
                              //  Criteria ope = new Criteria();
                               // ope.and(key).not().regex("^"+values[i]);
                                noLike[k] = Criteria.where(key).regex("^(?!"+values[k]+")");
                                //noLike[k] = Criteria.where(key).not().regex("^"+values[i]);
                            }
                        }

                        criteria.andOperator(noLike);
                    }
                    if (op.equals("in")){

                        criteria.and(key).in(values);
                        sqlWhere.put(key+" in ",valuesToString(values));

                    }
                    if (op.equals("nin")){

                        criteria.and(key).nin(values);
                        sqlWhere.put(key+" not in ( ","\'"+values.toString()+"%\')");
                    }
                    if (op.equals("or")){
                        criteria.orOperator(Criteria.where(key).is(val));
                        sqlWhere.put(" ( or "+key+" = ","\'"+val+"\') ");
                        map.put("or","true");
                    }
                    if (op.equals("orGt")){
                        criteria.orOperator(Criteria.where(key).gt(val));
                        continue;
                    }
                    if (op.equals("orGte")){
                        criteria.orOperator(Criteria.where(key).gte(val));
                        continue;
                    }
                    if (op.equals("orLt")){
                        criteria.orOperator(Criteria.where(key).lt(val));
                        continue;
                    }
                    if (op.equals("orLte")){
                        criteria.orOperator(Criteria.where(key).lte(val));
                        continue;
                    }
                    // or a in [.....]
                    if (op.equals("orIn")){
                        criteria.orOperator(Criteria.where(key).in(values));
                        sqlWhere.put(" ( or "+key+" in ( ","\'"+values+"\')) ");
                    }

                    if (op.equals("orMuls")){

                        if (fields != null && values != null){
                            Criteria or = new Criteria();
                            Criteria or2 = new Criteria();
                            Criteria or3 = new Criteria();
                            for (int k = 0;k<fields.length;k++){
                                if (fields[k].equals("S_STATUS")){
                                    String[] vs = new String[values.length-1];
                                    for (int t = 0;t<=values.length-2;t++){
                                        vs[t] = values[t];
                                    }
                                    or.and(fields[k]).in(vs);
                                  //  or.orOperator(Criteria.where(fields[k]).in(vs));
                                }
                                else if(fields[k].equals("CHECKFLAG")){
                                    or2.and(fields[k]).is(values[values.length-1]);
                                    //or.orOperator(Criteria.where(fields[k]).is(values[values.length-1]));
                                }
                                else {
                                    or3.orOperator(Criteria.where(fields[k]).is(values[k]));
                                }
                              //  or[k] = ope;
                                // continue;
                            }
                            Criteria andOr = new Criteria();
                            andOr.orOperator(or,or2,or3);
                            criteria.andOperator(andOr);
                        }
                    }

                    if (op.equals("andOrMul")){

                        if (fields != null && values != null){
                            Criteria[] or = new Criteria[fields.length];
                            for (int k = 0;k<fields.length;k++){
                                Criteria ope = new Criteria();
                                ope.orOperator(Criteria.where(fields[k]).is(values[k]));
                                or[k] = ope;
                                // continue;
                            }

                            criteria.andOperator(or);
                        }
                    }

                    //and( a < v1 or b < v2 )
                    //这里只是为了兼顾多个条件多个操作符的情况,操作符之间用"&"分割
                    String[] ops = op.split("&");
                    if (ops != null && ops.length>1){
                        if (fields != null && values != null){
                            Criteria[] criterias = getOrMutiples(ops, fields, values);
                            criteria.andOperator(criterias);
                        }
                    }

                    // or a = v1 or b = v2
                    if (op.equals("orMul")){
                        Criteria[] or = new Criteria[fields.length];
                        if (fields != null && values != null){
                            for (int k = 0;k<fields.length;k++){
                               or[k] =  Criteria.where(fields[k]).is(values[k]);
                                sqlWhere.put(" ( or "+fields[k]+" =  ","\'"+values[k]+"\') ");

                            }
                        }
                        criteria.orOperator(or);
                    }

                    if (op.equals("andMul")){
                        if (fields != null && values != null){
                            for (int k = 0;k<fields.length;k++){
                                criteria.and(fields[k]).is(values[k]);
                                sqlWhere.put(fields[k]+" = ","\'"+values[k]+"\'");
                            }
                        }
                    }

                    if (op.equals("orLikeInclude")){
                        Criteria[] or = new Criteria[fields.length];
                        if (fields != null && values != null){
                            for (int k = 0;k<fields.length;k++){
                              /*  Criteria ope = new Criteria();
                                ope.orOperator(Criteria.where(fields[k]).regex(values[k]));
                                or[k] = ope;*/
                                or[k] =  Criteria.where(fields[k]).regex(values[k]);
                            }
                        }
                        like = new Criteria();
                        like.orOperator(or);
                        //criteria.andOperator(or);
                        //criteria.andOperator(criteria);
                    }

                    if (op.equals("orLikeTop")){
                        Criteria[] or = new Criteria[fields.length];
                        if (fields != null && values != null){
                            for (int k = 0;k<fields.length;k++){
                                /*Criteria ope = new Criteria();
                                ope.orOperator(Criteria.where(fields[k]).regex("^"+values[k]));
                                or[k] = ope;*/
                                or[k] =  Criteria.where(fields[k]).regex("^"+values[k]);
                            }
                        }
                        like = new Criteria();
                        like.orOperator(or);
                        //criteria.andOperator(or);
                    }

                    if (op.equals("orLikeEnd")){
                        Criteria[] or = new Criteria[fields.length];
                        if (fields != null && values != null){
                            for (int k = 0;k<fields.length;k++){
                                /*Criteria ope = new Criteria();
                                ope.orOperator(Criteria.where(fields[k]).regex(values[k]+"$"));
                                or[k] = ope;*/
                                or[k] =  Criteria.where(fields[k]).regex("^"+values[k]);
                            }
                        }
                        like = new Criteria();
                        like.orOperator(or);
                    }
                }
                Query query = new BasicQuery(dbObject, basicDBObject);
                if (like != null){
                    criteria.andOperator(like);
                }
                if (pager == null){
                    pager = new Pager();
                }
                if (pager.isSum()){
                    map.put("criteria",criteria);
                }
                query.addCriteria(criteria);
                map.put("query",query);
                map.put("sql",sqlWhere);
                return map;
            }
            map.put("query",new Query());
            if (pager.isSum()){
                map.put("criteria",criteria);
            }
            return map;
        }catch (Exception e){
            long t1 = Calendar.getInstance().getTimeInMillis();
            logger.error("****************************************************");
            e.printStackTrace();
            logger.error("****************************************************");

            if (array == null){
            }else {
            }

            map.put("query",null);
            map.put("sql",null);
            return map;
        }

    }


    public String valuesToString(String[] values){
        StringBuffer stringBuffer = new StringBuffer();
        if (values != null && values.length >0){
            stringBuffer.append("( ");
            for (int l = 0; l < values.length; l++){
                stringBuffer.append("\'"+values[l]+"\'");
                if (l != values.length - 1){
                    stringBuffer.append(" , ");
                }
            }
            stringBuffer.append(" )");
        }
        return stringBuffer.toString();
    }

/*    public Map<String,Object> getData(JSONArray array, Pager pager, String collection, int sel) throws  Exception{
        Map<String,Object> result = new HashMap<String, Object>();
        PagerUtils pagerUtils = new PagerUtils();
        Map<String,Object> key = toCurd(array,pager);
        Query query = (Query) key.get("query");
        Criteria criteria = (Criteria) key.get("criteria");
        if (criteria != null){
            result.put("criteria",criteria);
        }
        if (pager == null){
            pager = new Pager();
        }
       // int pageSize = pager.getPageSize()-1;
        List<String> list =  pagerUtils.queryPager(query,pager,collection,sel);
        result.put("list",list);
        result.put("query",query);
        return result;
    }*/

    public List<String> baseCompareById(List<String> dataList) throws Exception{
        if (dataList != null && dataList.size() > 0){
            Collections.sort(dataList, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    JSONObject object1 = JSON.parseObject(o1.toString()).getJSONObject("_id");
                    JSONObject object2 = JSON.parseObject(o2.toString()).getJSONObject("_id");
                    ObjectId id1 = new ObjectId(object1.getString("$oid"));
                    ObjectId id2 = new ObjectId(object2.getString("$oid"));
                    if (id1.compareTo(id2) < 0){
                        return 1;
                    }
                    if (id1.compareTo(id2) == 0){
                        return 0;
                    }
                    return -1;
                }
            });
        }
        return dataList;
    }

    public List<String> baseCompareBySort(List<String> dataList, final String listCompareKey) throws Exception{
        if (dataList != null && dataList.size() > 0){
            Collections.sort(dataList, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    JSONObject ob1 = JSON.parseObject(o1);
                    JSONObject ob2 = JSON.parseObject(o2);
                    String s1 = ob1.getString(listCompareKey);
                    String s2 = ob2.getString(listCompareKey);

                   /* String id1 = ob1.getJSONObject("_id").getString("$oid");
                    String id2 = ob2.getJSONObject("_id").getString("$oid");

                    String _id1 = s1+id1;
                    String _id2 = s2+id2;

                    logger.info(_id1+"\n"+_id2);*/
                    if (s1.compareTo(s2) < 0 ){
                        return 1;
                    }
                    if (s1.compareTo(s2) == 0){
                        return 0;
                    }
                    return -1;
                }
            });
        }
        return dataList;
    }

   /* public List<String> queryCollection(Query query, String tableName) {
        long t0 = Calendar.getInstance().getTimeInMillis();
        try {
            return payTemplate.find(query,String.class,tableName);
        }catch (Exception e){
            long t1 = Calendar.getInstance().getTimeInMillis();
            LoggerInfo info = new LoggerInfo();
            LoggerParams loggerParams = new LoggerParams().params("queryCollection","BaseService Exception Query :"+e.toString(),new Pager(),t0,t1);
            info.log(loggerParams,"errLog");
            return null;
        }

    }*/

   public JSONArray jsonObject2JavaBean(JSONArray array) throws Exception{
       JSONArray dataArray = new JSONArray();
       if (array.isEmpty()){
           return array;
       }
       for (int i = 0; i<array.size();i++){
           JSONObject data = new JSONObject();
           JSONObject dataObject = array.getJSONObject(i);
           if (dataObject.containsKey("_id")){
               dataObject.remove("_id");
           }
           Set<Map.Entry<String,Object>> entrySet = dataObject.entrySet();
           Iterator<Map.Entry<String,Object>> iterator = entrySet.iterator();
           while (iterator.hasNext()){
               Map.Entry<String,Object> entry =  iterator.next();
               StringBuffer stb = new StringBuffer();
               String[] keys = entry.getKey().toLowerCase().split("_");
               for (int j = 0; j < keys.length;j++){
                   char[] chars = keys[j].toCharArray();
                       //直接通过ASCII码转换首字母大写，效率更高
                   chars[0]-=32;
                   stb.append(String.valueOf(chars));
               }
               //System.err.println(stb.toString());

               //最后将整个字段的首字母小写
               char[] keyChar = stb.toString().toCharArray();
               keyChar[0]+=32;
               String key  = String.valueOf(keyChar);

               data.put(key,entry.getValue());

           }
           dataArray.add(data);
       }
       array.clear();
       return dataArray;
   }

    public Criteria[] getOrMutiples (String[] ops, String[] fields, String[] values){

        Criteria[] criteria = new Criteria[ops.length];
        for (int i=0; i<ops.length; i++){
            criteria[i] = new Criteria();
            if("".equals(ops[i]) || StringUtils.isEmpty(ops[i])) continue;
            if (ops[i].equals(">")){
                criteria[i].orOperator(Criteria.where(fields[i]).gt(values[i]));
            }else if (ops[i].equals(">=")){
                criteria[i].orOperator(Criteria.where(fields[i]).gte(values[i]));
            }else if (ops[i].equals("<")){
                criteria[i].orOperator(Criteria.where(fields[i]).lt(values[i]));
            }else if (ops[i].equals("<=")){
                criteria[i].orOperator(Criteria.where(fields[i]).lte(values[i]));
            }else if (ops[i].equals("=")){
                criteria[i].orOperator(Criteria.where(fields[i]).is(values[i]));
            }
        }
        return criteria;
    }
}
