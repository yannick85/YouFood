<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.jsp" %>
<title>YouFood - Menu</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Menu : <c:out value="${menu.title}"/></h1>
<div class="choice-zone-restaurant">
	<h2>Restaurants associ&eacute;s :</h2>
	<div id="r_zone_yes" class="choice_zone">
		<div class="choice-zone-label">Restaurant avec ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listRestaurantChecked}" var="restaurantYes">
				<div nb="<c:out value="${restaurantYes.id}"/>" class="one_object r_yes"><c:out value="${restaurantYes.name}"/></div>
			</c:forEach>
		</div>
	</div>
	<div id="r_in" class="choice_button choice_in"></div>
	<div id="r_out" class="choice_button choice_out"></div>
	<div id="r_zone_no" class="choice_zone">
	<div class="choice-zone-label">Restaurant sans ce menu</div><div class="clear"></div>
	<div class="choice-objects">
		<c:forEach items="${listRestaurantNotChecked}" var="restaurantNo">
			<div nb="<c:out value="${restaurantNo.id}"/>" class="one_object r_no"><c:out value="${restaurantNo.name}"/><c:choose><c:when test="${restaurantNo.menu.id != 0 && restaurantNo.menu.id != null}"><span class="other_value_indicator"> (*)</span></c:when></c:choose></div>
		</c:forEach>
	</div>
	</div>
	<span class="other_value_legend">(*) Restaurant avec un autre menu s&eacute;lectionn&eacute;.</span>
	<div class="clear"></div>
</div>
<div class="choice-zone-products">
	<h2>Produits associ&eacute;s :</h2>
	<div class="choice-zone-starter">
		<div id="s_zone_yes" class="choice_zone">
		<div class="choice-zone-label">Entr&eacute;e dans ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listStarterChecked}" var="starterYes">
				<div nb="<c:out value="${starterYes.id}"/>" class="one_object s_yes"><c:out value="${starterYes.title}"/> (<c:out value="${starterYes.price}"/> &euro;)</div>
			</c:forEach>
		</div>
		</div>
		<div id="s_in" class="choice_button choice_in"></div>
		<div id="s_out" class="choice_button choice_out"></div>
		<div id="s_zone_no" class="choice_zone">
		<div class="choice-zone-label">Entr&eacute;e non compris dans ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listStarterNotChecked}" var="starterNo">
				<div nb="<c:out value="${starterNo.id}"/>" class="one_object s_no"><c:out value="${starterNo.title}"/> (<c:out value="${starterNo.price}"/> &euro;)</div>
			</c:forEach>
		</div>
		</div>
		<div class="clear"></div>
	</div>
		
	<div class="choice-zone-principal">
		<div id="p_zone_yes" class="choice_zone">
		<div class="choice-zone-label">Plat principal dans ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listPrincipalChecked}" var="principalYes">
				<div nb="<c:out value="${principalYes.id}"/>" class="one_object p_yes"><c:out value="${principalYes.title}"/> (<c:out value="${principalYes.price}"/> &euro;)</div>
			</c:forEach>
		</div>
		</div>
		<div id="p_in" class="choice_button choice_in"></div>
		<div id="p_out" class="choice_button choice_out"></div>
		<div id="p_zone_no" class="choice_zone">
		<div class="choice-zone-label">Plat principal non compris dans ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listPrincipalNotChecked}" var="principalNo">
				<div nb="<c:out value="${principalNo.id}"/>" class="one_object p_no"><c:out value="${principalNo.title}"/> (<c:out value="${principalNo.price}"/>$)</div>
			</c:forEach>
		</div>
		</div>
		<div class="clear"></div>
	</div>
	
	<div class="choice-zone-desert">
		<div id="d_zone_yes" class="choice_zone">
		<div class="choice-zone-label">Dessert dans ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listDesertChecked}" var="desertYes">
				<div nb="<c:out value="${desertYes.id}"/>" class="one_object d_yes"><c:out value="${desertYes.title}"/> (<c:out value="${desertYes.price}"/>$)</div>
			</c:forEach>
		</div>
		</div>
		<div id="d_in" class="choice_button choice_in"></div>
		<div id="d_out" class="choice_button choice_out"></div>
		<div id="d_zone_no" class="choice_zone">
		<div class="choice-zone-label">Dessert non compris dans ce menu</div><div class="clear"></div>
		<div class="choice-objects">
			<c:forEach items="${listDesertNotChecked}" var="desertNo">
				<div nb="<c:out value="${desertNo.id}"/>" class="one_object d_no"><c:out value="${desertNo.title}"/> (<c:out value="${desertNo.price}"/>$)</div>
			</c:forEach>
		</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
	
	<script type="text/javascript">
		var menuId = <c:out value="${menu.id}"/>;
		jQuery(".choice_zone .one_object").click(function(){
			jQuery("#choice_selected").attr("id","");
			jQuery(this).attr("id","choice_selected");
		});
		jQuery(".choice_in").click(function(){
			goIn();
		});
		jQuery(".choice_out").click(function(){
			goOut();
		});
		function doKey($key) {
			switch($key){
				case 37 ://<
					goIn();
					break;
				case 39 ://>
					goOut();
					break;
			}
			//alert($key);
		}
		jQuery("body").attr("onKeyPress","doKey(event.keyCode)");
		
		function goIn(){
			if(jQuery("#choice_selected").parent().parent('#r_zone_no').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/modify/restaurant/menu?r_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#r_zone_yes .choice-objects");
						jQuery("#choice_selected").find(".other_value_indicator").remove();
					}
				);
			}
			if(jQuery("#choice_selected").parent().parent('#s_zone_no').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/producttomenu/add?p_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#s_zone_yes .choice-objects");
					}
				);
			}
			if(jQuery("#choice_selected").parent().parent('#p_zone_no').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/producttomenu/add?p_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#p_zone_yes .choice-objects");
					}
				);
			}
			if(jQuery("#choice_selected").parent().parent('#d_zone_no').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/producttomenu/add?p_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#d_zone_yes .choice-objects");
					}
				);
			}
		}
		function goOut(){
			if(jQuery("#choice_selected").parent().parent('#r_zone_yes').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/modify/restaurant/menu?r_id='+number+'&m_id=0', 
					function(data) {
						jQuery("#choice_selected").appendTo("#r_zone_no .choice-objects");
					}
				);	
			}
			if(jQuery("#choice_selected").parent().parent('#s_zone_yes').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/producttomenu/remove?p_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#s_zone_no .choice-objects");
					}
				);	
			}
			if(jQuery("#choice_selected").parent().parent('#p_zone_yes').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/producttomenu/remove?p_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#p_zone_no .choice-objects");
					}
				);	
			}
			if(jQuery("#choice_selected").parent().parent('#d_zone_yes').length){
				number = jQuery("#choice_selected").attr("nb");
				jQuery.post('<%= contextPath %>/ajax/producttomenu/remove?p_id='+number+'&m_id='+menuId, 
					function(data) {
						jQuery("#choice_selected").appendTo("#d_zone_no .choice-objects");
					}
				);	
			}
		}
	</script>
<%@ include file="footer.jsp" %>
</body>
</html>