package com.sts.spring.web;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.type.TrueFalseType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sts.spring.bean.DataTables;
import com.sts.spring.dto.DataTableDto;
import com.sts.spring.service.DataTablesService;
import com.sts.spring.web.vo.DataTableResVo;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;
import com.sts.spring.common.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@RestController
public class DataTablesController {
	@Resource
	DataTablesService dataTablesService;

	/**
	 * @Title: getDataTablesList @Description: TODO() @param @return
	 *         设定文件 @return String 戻るタイプ @throws
	 */
	@RequestMapping("/getDataTablesList")
	public String getDataTablesList() {

		HashMap<String, Object> resMap = new HashMap<String, Object>();

		List<DataTables> dtList = dataTablesService.getDataTablesList();

		resMap.put("recordsTotal", dtList.size());
		resMap.put("recordsFiltered", dtList.size());
		resMap.put("data", dtList.size());

		/* dtList.subList(fromIndex, toIndex) */

		// String resJson = JSONUtils.toJSONString(resMap);
		JSONObject resJsonObj = new JSONObject();

		resJsonObj.put("sEcho", null);
		resJsonObj.put("iTotalRecords", dtList.size());// 实际的行数
		resJsonObj.put("iTotalDisplayRecords", dtList.size());// 显示的行数,这个要和上面写的一样

		List<String[]> lst = new ArrayList<String[]>();
		for (DataTables dt : dtList) {
			String[] str = { dt.getId().toString(), dt.getName(), dt.getCustomer_sn(), dt.getCity(), dt.getAddress(),
					dt.getContact_phone() };
			lst.add(str);
		}

		resJsonObj.put("aaData", lst);// 要以JSON格式返回

		return resJsonObj.toString();
	}

	@RequestMapping("/helloDataTables")
	public ModelAndView helloDataTables(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("DataTable");
	}

	@RequestMapping("/helloPage1")
	public ModelAndView helloPage1(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("page1");
	}

	@RequestMapping(value = "/tableDemoAjax", method = RequestMethod.POST)
	@ResponseBody
	public String tableDemoAjax(@RequestParam(value = "reqDraw") int iDisplayDraw,
			@RequestParam(value = "reqLength") int iDisplayLength, @RequestParam("reqStart") int iDisplayStart,
			@RequestParam("reqColumn") int iDisplayColumn, @RequestParam("reqDir") String iDisplayDir) {

		 // 生成20条测试数据
	    List<String[]> lst = new ArrayList<String[]>();
	    for (int i = 0; i < 52; i++) {
	        String[] d = { "col1_data" + i, "col2_data" + i };
	        lst.add(d);
	    }

		JSONObject getObj = new JSONObject();
		getObj.put("draw", iDisplayDraw+1);// datatableを描画する回数
		getObj.put("recordsTotal", lst.size());// レコード数
		getObj.put("recordsFiltered", lst.size());// 表示レコード数
		
		HashMap<Integer, List<String[]>> pagingMap = paging(lst,iDisplayLength);
		Integer pageIndex = (iDisplayStart / iDisplayLength)+1;//カレントページのインデックス
		List<String[]> subList = pagingMap.get(pageIndex);//
		getObj.put("data", subList);// JSON格式で戻る
		
		return getObj.toString();
	}
	@RequestMapping(value = "/tableDemoAjax2", method = RequestMethod.POST)
	@ResponseBody
	public String tableDemoAjax2(@RequestParam(value = "reqDraw") int iDisplayDraw,
			@RequestParam(value = "reqLength") int iDisplayLength, @RequestParam("reqStart") int iDisplayStart,
			@RequestParam("reqColumn") String iDisplayColumn, @RequestParam("reqDir") String iDisplayDir) {

		
		
		
		//　テストデータを生成
		DataTableResVo dataTableResVo = new DataTableResVo();
		List<DataTableDto> dataTableDtoList = new ArrayList<DataTableDto>();
		for (int i = 0; i < 22; i++) {
			
			DataTableDto dataTableDto = new DataTableDto();
			dataTableDto.setId(i);
			//dataTableDto.setName( "col2_data" + i);
			dataTableDto.setName(""+(char)(i+65)+(char)(i+65));
			dataTableDtoList.add(dataTableDto);
			//String[] d = { "co1_data" + i, "col2_data" + i };
		}
		 ListSortUtil<DataTableDto> lu = new ListSortUtil<DataTableDto>();
		 
		 lu.sortByMethod(dataTableDtoList,iDisplayColumn,iDisplayDir.toUpperCase().equals("DESC")?true:false);

		HashMap<Integer, List<DataTableDto>> pagingMap = paging2(dataTableDtoList,iDisplayLength);
		Integer pageIndex = (iDisplayStart / iDisplayLength)+1;//カレントページのインデックス
		List<DataTableDto> subList = pagingMap.get(pageIndex);//
		
		dataTableResVo.setData(subList);
		dataTableResVo.setDraw(iDisplayDraw+1);
		dataTableResVo.setError(null);
		dataTableResVo.setRecordsFiltered(dataTableDtoList.size());
		dataTableResVo.setRecordsTotal(dataTableDtoList.size());
		 
		
		//JSONObject getObj = JSONObject.fromObject(dataTableResVo);
		
		//JsonConfig jsonConfig =new JsonConfig();
		//jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String res = JSONArray.fromObject(dataTableResVo).toString();
		
		
		/*JSONObject getObj = new JSONObject();
		getObj.put("draw", iDisplayDraw+1);// datatableを描画する回数
		getObj.put("recordsTotal", lst.size());// レコード数
		getObj.put("recordsFiltered", lst.size());// 表示レコード数
		
		HashMap<Integer, List<String[]>> pagingMap = paging(lst,iDisplayLength);
		Integer pageIndex = (iDisplayStart / iDisplayLength)+1;//カレントページのインデックス
		List<String[]> subList = pagingMap.get(pageIndex);//
		getObj.put("data", subList);// JSON格式で戻る
		getObj.put("error", "");
		*/
		System.out.println(res);
		return res;
	}

	//
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<Integer, List<String[]>> paging(List<String[]> list, Integer pageSize) {
		int totalCount = list.size();
		int pageCount = 0;
		int m = totalCount % pageSize;

		if (m > 0) {
			pageCount = totalCount / pageSize + 1;
		} else {
			pageCount = totalCount / pageSize;
		}

		//List<String[]> subList = new ArrayList<String[]>();
		HashMap<Integer, List<String[]>> pageMap = new HashMap<Integer, List<String[]>>();
		for (int i = 1; i <= pageCount; i++) {
			if (m == 0) {
				//subList.addAll(list.subList((i - 1) * pageSize, i * pageSize));
				pageMap.put(i, list.subList((i - 1) * pageSize, i * pageSize));
			} else {
				if (i == pageCount) {
					//subList.addAll(list.subList((i - 1) * pageSize, totalCount));
					//System.out.println(subList);
					pageMap.put(i, list.subList((i - 1) * pageSize, totalCount));
				} else {
					//subList.addAll(list.subList((i - 1) * pageSize, i * pageSize));
					//System.out.println(subList);
					
					pageMap.put(i, list.subList((i - 1) * pageSize, i * pageSize));
				}
			}
		}

		return pageMap;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<Integer, List<DataTableDto>> paging2(List<DataTableDto> list, Integer pageSize) {
		int totalCount = list.size();
		int pageCount = 0;
		int m = totalCount % pageSize;

		if (m > 0) {
			pageCount = totalCount / pageSize + 1;
		} else {
			pageCount = totalCount / pageSize;
		}

		//List<String[]> subList = new ArrayList<String[]>();
		HashMap<Integer, List<DataTableDto>> pageMap = new HashMap<Integer, List<DataTableDto>>();
		for (int i = 1; i <= pageCount; i++) {
			if (m == 0) {
				//subList.addAll(list.subList((i - 1) * pageSize, i * pageSize));
				pageMap.put(i, list.subList((i - 1) * pageSize, i * pageSize));
			} else {
				if (i == pageCount) {
					//subList.addAll(list.subList((i - 1) * pageSize, totalCount));
					//System.out.println(subList);
					pageMap.put(i, list.subList((i - 1) * pageSize, totalCount));
				} else {
					//subList.addAll(list.subList((i - 1) * pageSize, i * pageSize));
					//System.out.println(subList);
					
					pageMap.put(i, list.subList((i - 1) * pageSize, i * pageSize));
				}
			}
		}

		return pageMap;
	}
	
}
