<%@ page pageEncoding="utf-8" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

	<form:form action="/product/SaveOrUpdate" modelAttribute="item" method="post" enctype="multipart/form-data">
			<form>
			<div class="card">
				<div class="card-body">
					<div class="row">
						<div class="col-3">
							<div class="p-3">
								<img src="/images/${item.image}" alt="" style="width: 250px; height: 2	50px;">
								<div class="form-group">
									<input style="margin-top: 10px ;background: #009879;border-radius: 2px;" type="file" name="image" class="form-control-file"
										id="image" onchange="chooseFile(this)" />
								</div>
							</div>
						</div>
						<div class="col-9">
							<div class="form-group">
								<label for="youtubeid">Product name</label>
								<form:input class="form-control" path="name" placeholder="Products name?" />
								<small id="youtubeIHid" class="form-text text-muted"></small>
							</div>
							<div class="form-group">
								<label for="videotitle">Product price</label>
								<form:input class="form-control" path="price" placeholder="Products price?" />
								<small id="videotHid" class="form-text text-muted"></small>
							</div>
							<div class="form-group">
								<label for="videotitle">Category</label>
								<form:input class="form-control" name="category" path="category.id" placeholder="Category ?" />
								<small id="videotHid" class="form-text text-muted"></small>
							</div>

						</div>
					</div>

				</div>
				<div class="card-footer text-muted"  style="background: #696969;position: relative;">
					<button id="a" style='--clr:#1e9bff' type="submit"><span id="span">Create</span><i id="i"></i></button>
					<button id="a"  style='--clr:#40E0D0' formaction="/product/index" formmethod="get"><span id="span">Reset</span><i id="i"></i></button>
				</div>
			</div>
			</form>
		</form:form>