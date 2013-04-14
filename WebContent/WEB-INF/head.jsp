<%@page import="com.rman.youfood.dao.DaoFactory"%>
<%@page import="com.rman.youfood.dao.RestaurantDao"%>
<%@page import="com.rman.youfood.entity.*"%>
<%@page import="java.util.List"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="<%= getServletContext().getContextPath() %>/style.css" />
<link type="text/css" rel="stylesheet" href="<%= getServletContext().getContextPath() %>/fancybox/jquery.fancybox-1.3.4.css" />
<script src="<%= getServletContext().getContextPath() %>/jquery-1.7.2.min.js"></script>
<script src="<%= getServletContext().getContextPath() %>/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<% String contextPath = getServletContext().getContextPath()+"/yf/";
RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
List<Restaurant> restaurantWithoutMenu =restaurantDao.getRestaurantWhithoutMenu(); request.setAttribute("restaurantWithoutMenu", restaurantWithoutMenu);%>
<script type="text/javascript">
jQuery(document).ready(function(){
	jQuery(".fancybox-iframe").fancybox({
		'type'				: 'iframe',
		onClosed: function(){
			location.reload();
		}
	});}
);
function deleteEntity(ent,id,name){
	if(confirm("Etes vous sur de vouloir supprimer "+name+" ?")){
		jQuery.post("<%= contextPath %>/ajax/delete?ent="+ent+"&id="+id, 
				function(data) {
					alert(data);
					jQuery("#"+ent+"-"+id).remove();
				}
			);	
	}
}
</script>