<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload to Database</title>
</head>
<body>
	<center>
		<h1>Automated Gray & White Matter Segmentation with Apache Spark</h1>
		<h2>Please upload region of interest (ROI) to database</h2>
		<form method="post" action="Results" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Enter Image Volume Name</td>
					<td><input type="text" name="file_name"/></td>
				</tr>
				<tr>
					<td>Upload Volume:</td>
					<td><input type="file" name="volume" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="Upload">
					</td>
				</tr>
			</table>
		</form>
		
		
		
	</center>
</body>
</html>