<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleControladorPublishProxyid" scope="session" class="org.pap.publicadores.ControladorPublishProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleControladorPublishProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleControladorPublishProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleControladorPublishProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        org.pap.publicadores.ControladorPublish getControladorPublish10mtemp = sampleControladorPublishProxyid.getControladorPublish();
if(getControladorPublish10mtemp == null){
%>
<%=getControladorPublish10mtemp %>
<%
}else{
        if(getControladorPublish10mtemp!= null){
        String tempreturnp11 = getControladorPublish10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String arg0_1id=  request.getParameter("arg016");
            java.lang.String arg0_1idTemp = null;
        if(!arg0_1id.equals("")){
         arg0_1idTemp  = arg0_1id;
        }
        String arg1_2id=  request.getParameter("arg118");
            java.lang.String arg1_2idTemp = null;
        if(!arg1_2id.equals("")){
         arg1_2idTemp  = arg1_2id;
        }
        boolean inicioSecion13mtemp = sampleControladorPublishProxyid.inicioSecion(arg0_1idTemp,arg1_2idTemp);
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(inicioSecion13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
break;
case 20:
        gotMethod = true;
        String arg0_3id=  request.getParameter("arg023");
        int arg0_3idTemp  = Integer.parseInt(arg0_3id);
        %>
        <jsp:useBean id="org1pap1publicadores1LocalDateTime_4id" scope="session" class="org.pap.publicadores.LocalDateTime" />
        <%
        String arg2_5id=  request.getParameter("arg227");
            java.lang.String arg2_5idTemp = null;
        if(!arg2_5id.equals("")){
         arg2_5idTemp  = arg2_5id;
        }
        sampleControladorPublishProxyid.modificarDistribucion(arg0_3idTemp,org1pap1publicadores1LocalDateTime_4id,arg2_5idTemp);
break;
case 29:
        gotMethod = true;
        org.pap.publicadores.DtDistribucion[] listarDistribuciones29mtemp = sampleControladorPublishProxyid.listarDistribuciones();
if(listarDistribuciones29mtemp == null){
%>
<%=listarDistribuciones29mtemp %>
<%
}else{
        String tempreturnp30 = null;
        if(listarDistribuciones29mtemp != null){
        java.util.List listreturnp30= java.util.Arrays.asList(listarDistribuciones29mtemp);
        tempreturnp30 = listreturnp30.toString();
        }
        %>
        <%=tempreturnp30%>
        <%
}
break;
case 32:
        gotMethod = true;
        String arg0_6id=  request.getParameter("arg035");
            java.lang.String arg0_6idTemp = null;
        if(!arg0_6id.equals("")){
         arg0_6idTemp  = arg0_6id;
        }
        org.pap.publicadores.DtUsuario obtenerUsuario32mtemp = sampleControladorPublishProxyid.obtenerUsuario(arg0_6idTemp);
if(obtenerUsuario32mtemp == null){
%>
<%=obtenerUsuario32mtemp %>
<%
}else{
        if(obtenerUsuario32mtemp!= null){
        String tempreturnp33 = obtenerUsuario32mtemp.toString();
        %>
        <%=tempreturnp33%>
        <%
        }}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>