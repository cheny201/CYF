<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.bstek.com/dorado" prefix="d" %>
<html>
<d:DropDownView clazz="com.bstek.dorado.view.control.dropdown.DynamicDropDownViewModel" clientType="smartweb2">
<body style="background: #F5F7F9">
<table cellspacing="0" cellpadding="4" style="width: 100%; height: 100%; table-layout: fixed">
  <%
  String filterParameter = (String)request.getAttribute("dorado.filter-parameter");
  if (filterParameter != null && filterParameter.length() > 0) {
  %>
  <tr>
    <td>
      <table border="0" width="100%" cellspacing="0" cellpadding="0" style="border-collapse: collapse">
        <tr>
          <td nowrap>&nbsp;<d:I18N base="smartweb/v2/server" key="__DYNA_DROPDOWN_FILTER_LABEL" />&nbsp;</td>
          <td nowrap width="100%"><d:TextEditor id="editorFilter" width="100%" /></td>
          <td nowrap>&nbsp;<d:Button id="buttonFilter" /></td>
        </tr>
      </table>
    </td>
  </tr>
  <%}%>
  <tr style="height: 100%">
    <td>
      <d:DataTable id="tableDropDown" />
    </td>
  </tr>
  <tr>
    <td align="right" nowrap>
      <d:I18N base="smartweb/v2/server" key="__DYNA_DROPDOWN_RECORD_COUNT" /><label id="labelRecordCount"></label>
    </td>
  </tr>
</table>
</body>

<script>
var editorFilter = document.getElementById("editorFilter");
var tableDropDown = document.getElementById("tableDropDown");
var buttonFilter = document.getElementById("buttonFilter");

function initializeFilterEditor() {
  if (editorFilter != null) {
    editorFilter.onKeyDown = function(evt) {
      switch (evt.keyCode) {
        case 13: { // enter
          if (!evt.ctrlKey) {
            buttonFilter.click();
          }
          else {
            DropDown.closeFrame();
          }
          break;
        }

        default: { // down
          tableDropDown.onKeyDown(evt);
          break;
        }
      }
    }
  }
}

setTimeout("initializeFilterEditor();", 0);
</script>

</d:DropDownView>
</html>
