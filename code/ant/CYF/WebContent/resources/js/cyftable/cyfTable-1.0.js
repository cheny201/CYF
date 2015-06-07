var cyfTables = new cyfMap();

function cyf_Table(id,colModels){
	this.id = id;//控件id
	this.colModels = colModels;
	this.autowidth = false;
	this.table = null;
	this.queryData = null;//查询条件
	this.width = null;
	this.height = 300;
	this.page = null;
	this.pageTag = null;
	this.pageSize = 10;
	this.pageAlign = "center";
	this.url = "";
	this.showRowNum = false;
	this.showPage = false;
	this.initList = function (params){
		cyfTables.put(this.id, this);
		this.page = new cyf_Page();
		document.getElementById(this.id).innerHTML = "";
		
		parsePro(this,params);
		
		this.table = document.createElement("table");
		this.table.setAttribute("class", "cyf_table");
		this.table.setAttribute("cellspacing", "0");
		
		this.createTableHead();
		this.createTableBody();
		
		var div = document.createElement("div");
		div.setAttribute("class", "cyf_table_div");
		div.style.height = this.height+"px";
		div.appendChild(this.table);
		document.getElementById(this.id).appendChild(div);
		
		if(this.showPage){
			this.createPage();
		}
	};
	this.createTableBody = function(){
		var tbody = document.createElement("tbody");
		this.table.appendChild(tbody);
	};
	this.setDataSet = function (dataSet){
		if(isEmpty(dataSet)){
			this.page.data = new Array();
		}else{
			this.page.data = dataSet;
		}
		this.clearData();
		
		var tbody =  this.table.childNodes[1];
		var index = ((this.page.pageNo-1)*this.page.pageSize) +1;
		for ( var int = 0; int < this.page.data.length; int++) {
			if(int >= this.page.pageSize){
				break;
			}
			
			var tr = document.createElement("tr");
			var data = this.page.data[int];
			if(this.showRowNum){
				var td = createTD();
				td.innerHTML = int+index;
				tr.appendChild(td);
			}
			for ( var j = 0; j < this.colModels.length; j++) {
				var model = this.colModels[j];
				var td = createTD();
				var value = null;
				for ( var prot in data) {
					if(prot == model.id){
						value = data[prot];
						break;
					}
				}
				value = isEmpty(value)? "":value;
				td.innerHTML = value;
				tr.appendChild(td);
			}
			tbody.appendChild(tr);
		}
		this.table.appendChild(tbody);
		if(this.showPage){
			this.pageTag.childNodes[4].innerHTML = this.page.pageNo;//当前页
			this.pageTag.childNodes[6].innerHTML = this.page.pageCount;//总页数
			this.pageTag.childNodes[16].innerHTML = this.page.entityCount;//总记录数
		}
		
		
	};
	this.createTableHead = function(){
		var thead = document.createElement("thead");
		var tr = document.createElement("tr");
		var width = 0;
		if(this.showRowNum){
			var th = document.createElement("th");
			th.setAttribute("class", "cyf_th");
			th.style.paddingLeft = "20px";
			th.style.paddingRight = "20px";
			th.innerHTML = "";
			tr.appendChild(th);
			width += 50;
		}
		
		var colNum = this.colModels.length;
		var w = (1/colNum)*100;
		for ( var int = 0; int < this.colModels.length; int++) {
			var model = this.colModels[int];
			var th = document.createElement("th");
			th.setAttribute("id", model.id);
			th.setAttribute("class", "cyf_th");
			th.style.height = model.heigth+(isPercent(model.heigth)?"":"px");
			
			if(this.autowidth){
				th.style.width = w+"%";
			}else{
				th.style.width = model.width+(isPercent(model.width)?"":"px");
				width += model.width;
			}
			
			var div = document.createElement("div");
			div.innerHTML = model.name;
			th.appendChild(div);
			tr.appendChild(th);
		}
		thead.appendChild(tr);
		if(this.autowidth){
			this.table.style.width="100%";
		}else{
			this.table.style.width=width+"px";
		}
		
		this.table.appendChild(thead);
	};
	this.clearData = function(){
		this.table.removeChild(this.table.childNodes[1]);
		this.createTableBody();
	};
	this.createPage = function(){
		this.pageTag = document.createElement("div");
		this.pageTag.setAttribute("id",this.id+"_page");
		this.pageTag.setAttribute("class","cyf_page_div");
		this.pageTag.style.textAlign = this.pageAlign;
		var html = "<a name=\""+this.id+"\" href=\"javascript:void(0);\" onclick=\"queryData(this.name,0)\">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a name=\""+this.id+"\" href=\"javascript:void(0);\" onclick=\"queryData(this.name,1)\">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<span>0</span>/<span>0</span>&nbsp;&nbsp;&nbsp;&nbsp;<a name=\""+this.id+"\" href=\"javascript:void(0);\" onclick=\"queryData(this.name,2)\">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a name=\""+this.id+"\" href=\"javascript:void(0);\" onclick=\"queryData(this.name,3)\">末页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a name=\""+this.id+"\" href=\"javascript:void(0);\" onclick=\"queryData(this.name,4)\">跳转到</a>&nbsp;&nbsp;第&nbsp;<input id='"+this.id+"_page_index' type='text' class='cyf_page_text' value='1'/>&nbsp;页&nbsp;&nbsp;&nbsp;共&nbsp;&nbsp;<span>0</span>&nbsp;&nbsp;条&nbsp;&nbsp;每页<input type='text' name=\""+this.id+"\" onkeyup='reload(this.name,this.value,event);' class='cyf_page_text'/>条";
		this.pageTag.innerHTML = html;
		document.getElementById(this.id).appendChild(this.pageTag);
	};
	this.loadData = function(type){
		var p = this.page;
		this.page.pageSize = this.pageSize;
		if(isEmpty(this.queryData)){
			this.queryData = new Object();
		}
		if(isEmpty(type)){
			type = 0;
		}
		var queryPage = p.pageNo;
		if(type == 1){
			queryPage = p.pageNo -1;
			queryPage = queryPage < 1 ? 1:queryPage;
		}else if(type == 2){
			queryPage = p.pageNo + 1;
			queryPage = queryPage > p.pageCount ? p.pageCount:queryPage;
		}else if(type == 3){
			queryPage = p.pageCount;
		}else if(type == 4){
			var i = document.getElementById(this.id+"_page_index").value;
			if(isNaN(i)){
				alert("无效的页码");
				return;
			}
			queryPage = parseInt(i);
			if(queryPage >= p.pageCount || queryPage < 1){
				alert("无效的页码");
				return;
			}
		}
		queryData.pageSize = p.pageSize;
		queryData.pageNo = queryPage;
		$.ajax({
			async : false,
			type : "POST",
			url : this.url,
			data : queryData,
			dataType : 'json',
			success : function(resp) {
				p.data = resp.data;
				p.pageNo = resp.pageNo;
				p.pageSize = resp.pageSize;
				p.pageCount = resp.pageCount;
				p.entityCount = resp.entityCount;
			}
		});
		this.setDataSet(this.page.data);
	};
}
function cyf_colModel(id,name,width,height){
	this.id = id;
	this.name = name;
	this.width = isEmpty(width)?"200px":width;
	this.heigth = isEmpty(height)?"20px":height;
}

function cyf_Page(){
	this.pageNo = 1;
	this.pageSize = 10;
	this.pageCount = 1;
	this.entityCount = 0;
	this.data = null;
}

function createTD(){
	var td = document.createElement("td");
	td.setAttribute("class", "cyf_td");
	return td;
}
function cyfMap(){
	this.keys = new Array();
	this.objs = new Array();
	this.getIndex = function(key){
		for ( var i = 0; i < this.keys.length; i++) {
			if(this.keys[i] == key){
				return i;
			}
		}
		return null;
	};
	this.get = function(key){
		var index = this.getIndex(key);
		if(index != null){
			return this.objs[index];
		}
		return null;
	};
	this.hasKey = function(key){
		var index = this.getIndex(key);
		if(index != null){
			return true;
		}
		return false;
	};
	this.put = function(key,obj){
		var index = this.getIndex(key);
		if(index != null){
			this.objs[index] = obj;
		}else{
			var i = this.getIndex(null);
			if(i == null){
				this.keys.push(key);
				this.objs.push(obj);
			}else{
				this.keys[i] = key;
				this.objs[i] = obj;
			}
		}
	};
	this.remove = function(key){
		var index = this.getIndex(key);
		if(index != null){
			this.keys[index] = null;
			this.objs[index] = null;
		};
	};
}

/**
 * 判断字符串是否为空
 * 
 * @param value
 * @returns {Boolean}
 */
function isEmpty(value) {
	return (value == undefined || value == null || value == "");
}

/**
 * 换页操作
 * @param id
 * @param type
 * @returns {queryData}
 */
function queryData(id,type){
	var t = cyfTables.get(id);
	if(!isEmpty(t)){
		t.loadData(type);
	}
};

/**
 * 修改每页数据量
 * @param id
 * @param value
 * @param e
 */
function reload(id,value,e){
	if(e.keyCode == 13){
		var t = cyfTables.get(id);
		if(!isEmpty(t)){
			t.pageSize = value;
			t.loadData();
		}
	}
}

/**
 * 判断是否是百分数
 * @param value
 * @returns {Boolean}
 */
function isPercent(value){
	if(!isEmpty(value)){
		value = value + "";
		var c = value.charAt(value.length-1);
		if(c == "%"){
			return true;
		}
	}
	return false;
}

/**
 * 属性解析
 * @param template
 * @param source
 */
function parsePro(template,source){
	for ( var pro in template) {
		var c = source[pro];
		if(c != undefined){
			template[pro] = c;
		}
	}
}