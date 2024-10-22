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
        String arg0_1id=  request.getParameter("arg022");
            java.lang.String arg0_1idTemp = null;
        if(!arg0_1id.equals("")){
         arg0_1idTemp  = arg0_1id;
        }
        org.pap.publicadores.DtUsuario obtenerUsuario13mtemp = sampleControladorPublishProxyid.obtenerUsuario(arg0_1idTemp);
if(obtenerUsuario13mtemp == null){
%>
<%=obtenerUsuario13mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">email:</TD>
<TD>
<%
if(obtenerUsuario13mtemp != null){
java.lang.String typeemail16 = obtenerUsuario13mtemp.getEmail();
        String tempResultemail16 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeemail16));
        %>
        <%= tempResultemail16 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">password:</TD>
<TD>
<%
if(obtenerUsuario13mtemp != null){
java.lang.String typepassword18 = obtenerUsuario13mtemp.getPassword();
        String tempResultpassword18 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typepassword18));
        %>
        <%= tempResultpassword18 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">nombre:</TD>
<TD>
<%
if(obtenerUsuario13mtemp != null){
java.lang.String typenombre20 = obtenerUsuario13mtemp.getNombre();
        String tempResultnombre20 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typenombre20));
        %>
        <%= tempResultnombre20 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
case 24:
        gotMethod = true;
        String arg0_2id=  request.getParameter("arg027");
            java.lang.String arg0_2idTemp = null;
        if(!arg0_2id.equals("")){
         arg0_2idTemp  = arg0_2id;
        }
        org.pap.publicadores.DtDistribucion[] listarDistribucionesPorEstado24mtemp = sampleControladorPublishProxyid.listarDistribucionesPorEstado(arg0_2idTemp);
if(listarDistribucionesPorEstado24mtemp == null){
%>
<%=listarDistribucionesPorEstado24mtemp %>
<%
}else{
        String tempreturnp25 = null;
        if(listarDistribucionesPorEstado24mtemp != null){
        java.util.List listreturnp25= java.util.Arrays.asList(listarDistribucionesPorEstado24mtemp);
        tempreturnp25 = listreturnp25.toString();
        }
        %>
        <%=tempreturnp25%>
        <%
}
break;
case 29:
        gotMethod = true;
        String arg0_3id=  request.getParameter("arg032");
            java.lang.String arg0_3idTemp = null;
        if(!arg0_3id.equals("")){
         arg0_3idTemp  = arg0_3id;
        }
        org.pap.publicadores.DtDistribucion[] listarDistribucionesPorZona29mtemp = sampleControladorPublishProxyid.listarDistribucionesPorZona(arg0_3idTemp);
if(listarDistribucionesPorZona29mtemp == null){
%>
<%=listarDistribucionesPorZona29mtemp %>
<%
}else{
        String tempreturnp30 = null;
        if(listarDistribucionesPorZona29mtemp != null){
        java.util.List listreturnp30= java.util.Arrays.asList(listarDistribucionesPorZona29mtemp);
        tempreturnp30 = listreturnp30.toString();
        }
        %>
        <%=tempreturnp30%>
        <%
}
break;
case 34:
        gotMethod = true;
        String arg0_4id=  request.getParameter("arg037");
            java.lang.String arg0_4idTemp = null;
        if(!arg0_4id.equals("")){
         arg0_4idTemp  = arg0_4id;
        }
        String arg1_5id=  request.getParameter("arg139");
            java.lang.String arg1_5idTemp = null;
        if(!arg1_5id.equals("")){
         arg1_5idTemp  = arg1_5id;
        }
        String arg2_6id=  request.getParameter("arg241");
            java.lang.String arg2_6idTemp = null;
        if(!arg2_6id.equals("")){
         arg2_6idTemp  = arg2_6id;
        }
        String arg3_7id=  request.getParameter("arg343");
            java.lang.String arg3_7idTemp = null;
        if(!arg3_7id.equals("")){
         arg3_7idTemp  = arg3_7id;
        }
        String arg4_8id=  request.getParameter("arg445");
            java.util.Calendar arg4_8idTemp = null;
        if(!arg4_8id.equals("")){
        java.text.DateFormat dateFormatarg445 = java.text.DateFormat.getDateInstance();
        java.util.Date dateTemparg445  = dateFormatarg445.parse(arg4_8id);
         arg4_8idTemp = new java.util.GregorianCalendar();
        arg4_8idTemp.setTime(dateTemparg445);
        }
        String arg5_9id=  request.getParameter("arg547");
            java.lang.String arg5_9idTemp = null;
        if(!arg5_9id.equals("")){
         arg5_9idTemp  = arg5_9id;
        }
        String arg6_10id=  request.getParameter("arg649");
            java.lang.String arg6_10idTemp = null;
        if(!arg6_10id.equals("")){
         arg6_10idTemp  = arg6_10id;
        }
        sampleControladorPublishProxyid.modificarBeneficiario(arg0_4idTemp,arg1_5idTemp,arg2_6idTemp,arg3_7idTemp,arg4_8idTemp,arg5_9idTemp,arg6_10idTemp);
break;
case 51:
        gotMethod = true;
        org.pap.publicadores.DtDistribucion[] listarDistribuciones51mtemp = sampleControladorPublishProxyid.listarDistribuciones();
if(listarDistribuciones51mtemp == null){
%>
<%=listarDistribuciones51mtemp %>
<%
}else{
        String tempreturnp52 = null;
        if(listarDistribuciones51mtemp != null){
        java.util.List listreturnp52= java.util.Arrays.asList(listarDistribuciones51mtemp);
        tempreturnp52 = listreturnp52.toString();
        }
        %>
        <%=tempreturnp52%>
        <%
}
break;
case 54:
        gotMethod = true;
        String arg0_11id=  request.getParameter("arg057");
            java.lang.String arg0_11idTemp = null;
        if(!arg0_11id.equals("")){
         arg0_11idTemp  = arg0_11id;
        }
        String arg1_12id=  request.getParameter("arg159");
            java.lang.String arg1_12idTemp = null;
        if(!arg1_12id.equals("")){
         arg1_12idTemp  = arg1_12id;
        }
        String arg2_13id=  request.getParameter("arg261");
            java.lang.String arg2_13idTemp = null;
        if(!arg2_13id.equals("")){
         arg2_13idTemp  = arg2_13id;
        }
        String arg3_14id=  request.getParameter("arg363");
            java.lang.String arg3_14idTemp = null;
        if(!arg3_14id.equals("")){
         arg3_14idTemp  = arg3_14id;
        }
        sampleControladorPublishProxyid.modificarRepartidor(arg0_11idTemp,arg1_12idTemp,arg2_13idTemp,arg3_14idTemp);
break;
case 65:
        gotMethod = true;
        String arg0_15id=  request.getParameter("arg068");
            java.lang.String arg0_15idTemp = null;
        if(!arg0_15id.equals("")){
         arg0_15idTemp  = arg0_15id;
        }
        String arg1_16id=  request.getParameter("arg170");
            java.lang.String arg1_16idTemp = null;
        if(!arg1_16id.equals("")){
         arg1_16idTemp  = arg1_16id;
        }
        boolean inicioSecion65mtemp = sampleControladorPublishProxyid.inicioSecion(arg0_15idTemp,arg1_16idTemp);
        String tempResultreturnp66 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(inicioSecion65mtemp));
        %>
        <%= tempResultreturnp66 %>
        <%
break;
case 72:
        gotMethod = true;
        String arg0_17id=  request.getParameter("arg075");
        int arg0_17idTemp  = Integer.parseInt(arg0_17id);
        String arg1_18id=  request.getParameter("arg177");
            java.util.Calendar arg1_18idTemp = null;
        if(!arg1_18id.equals("")){
        java.text.DateFormat dateFormatarg177 = java.text.DateFormat.getDateInstance();
        java.util.Date dateTemparg177  = dateFormatarg177.parse(arg1_18id);
         arg1_18idTemp = new java.util.GregorianCalendar();
        arg1_18idTemp.setTime(dateTemparg177);
        }
        String arg2_19id=  request.getParameter("arg279");
            java.lang.String arg2_19idTemp = null;
        if(!arg2_19id.equals("")){
         arg2_19idTemp  = arg2_19id;
        }
        sampleControladorPublishProxyid.modificarDistribucion(arg0_17idTemp,arg1_18idTemp,arg2_19idTemp);
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