<template>
	<div class="diy_details_box diy_div_pet_adoption">
		<div class="warp">
			<div class="container">
					<div class="details_title">详情</div>
				<div class="row">
						<div class="row_top_wrap">
						<!-- 图片 -->
							<div class="row_1" v-if="imgList.length">
									<div class="diy_img" v-for="(item,index) in imgList" :key="item+index">
									<img :src="$fullUrl(obj[item.name])" />
								</div>
							</div>
							<!-- 内容 -->
							<div class="row_2" :class="{flex_row2: !imgList.length}">
								<div :class="{flex_row2_item: !imgList.length}" v-for="(item,index) in itemList" :key="item+index" v-show="$check_field('get',item.name)">
							<div class="view">
								<div class="diy_title">
									<span>{{item.title}}:</span>
								</div>
							
								<div class="diy_field diy_uid" :class="{flex_row2_item_text:imgList.length}" v-if="item.type == 'UID'">
									<span>
										{{ get_user_info(obj[item.name]) }}
									</span>
								</div>
								<div class="diy_field diy_video" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '视频'">
									<router-link :to="'/media/video?filename=' + $fullUrl(obj[item.name])" v-if="obj[item.name]" >
										<span>
											查看视频
										</span>
									</router-link>
								</div>
								<div class="diy_field diy_music" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '音频'">
									<audio v-if="obj[item.name]" style="text-align: left" :src="$fullUrl(obj[item.name])" controls></audio>
								</div>
								<div class="diy_field diy_music" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '文件'">
									<a :href="$fullUrl(obj[item.name])" target="_blank" style="color: rgb(64, 158, 255);">点击下载</a>
								</div>
								<div class="diy_field diy_date" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '日期'">
									<span>
										{{ $toTime(obj[item.name] ,"yyyy-MM-dd") }}
									</span>
								</div>
								<div class="diy_field diy_time" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '时间'">
									<span>
										{{ $toTime(obj[item.name] ,"hh:mm:ss") }}
									</span>
								</div>
								<div class="diy_field diy_datetime" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '日长'">
									<span>
										{{ $toTime(obj[item.name] ,"yyyy-MM-dd hh:mm:ss") }}
									</span>
								</div>
								<div class="diy_field diy_phone" :class="{flex_row2_item_text:imgList.length}"  v-else-if="item.type == '电话' || item.type == '手机'">
									<span>
										{{ obj[item.name] }}
									</span>
								</div>
								<div class="diy_field diy_number" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '数字'">
									<span>
										{{ obj[item.name] }}
									</span>
								</div>
								<div class="diy_field diy_desc" :class="{flex_row2_item_text:imgList.length}" v-else-if="item.type == '多文本'">
									<div>
										{{ obj[item.name] }}
									</div>
								</div>
								<!-- <div class="diy_field diy_html"  v-else-if="item.type == '编辑'" v-html="obj[item.name]" >

								</div> -->
								<div class="diy_field diy_text" :class="{flex_row2_item_text:imgList.length}" v-else>
									<span>
										{{ obj[item.name] }}
									</span>
								</div>
							</div>
						</div>
							</div>
					</div>
							<div class="details_btn_wrap">
							</div>
						<!-- 富文本 -->
				<div class="rich_text" v-for="(item,index) in richList" :key="item+index" v-show="$check_field('get',item.name)">
						<div class="view">
						<div class="diy_title">
							<span v-text="item.title"></span>
						</div>
							<div class="diy_html" v-html="obj[item.name]" >

						</div>
						</div>
				</div>
			
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";
	import mixinPage from "@/mixins/page.js";

	export default {
		mixins: [mixin,mixinPage],
		components: {

		},
		props: {
			obj: {
				type: Object,
				default: function() {
					return {};
				},
			}
		},
		data() {
			return {
				praiseLen: this.obj["praise_len"],
				imgList: [
				],
				itemList: [
						{
							title: "宠物编号",
							name: "pet_number",
							type: "文本"
						},
						{
							title: "标题",
							name: "title",
							type: "文本"
						},
						{
							title: "性别",
							name: "gender",
							type: "下拉"
						},
						{
							title: "年龄",
							name: "age",
							type: "文本"
						},
						{
							title: "发布者编号",
							name: "publisher_number",
							type: "UID"
						},
						{
							title: "发布者姓名",
							name: "publisher_name",
							type: "文本"
						},
						{
							title: "领养者编号",
							name: "adopter_number",
							type: "UID"
						},
						{
							title: "领养者姓名",
							name: "adopter_name",
							type: "文本"
						},
						{
							title: "领养者电话",
							name: "adopter_telephone",
							type: "文本"
						},
				],
				richList: [
						{
							title: "领养说明",
							name: "adoption_instructions",
							type: "多文本"
						},
				],
				// 用户列表
				list_user_publisher_number: [],
				// 用户列表
				list_user_adopter_number: [],
			};
		},
		methods: {
			/**
			 * 获取发布者用户列表
			 */
			async get_list_user_publisher_number() {
				var json = await this.$get("~/api/user/get_list?user_group=发布者");
				if(json.result && json.result.list){
					this.list_user_publisher_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_info(id){
				var obj = this.list_user_publisher_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
			/**
			 * 获取领养者用户列表
			 */
			async get_list_user_adopter_number() {
				var json = await this.$get("~/api/user/get_list?user_group=领养者");
				if(json.result && json.result.list){
					this.list_user_adopter_number = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_info(id){
				var obj = this.list_user_adopter_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
				/**
			 * 改变点赞数
			 */
			change_praise(obj) {
				var user_id = this.user.user_id;

				var query = {
					source_table: "pet_adoption",
					source_field: "pet_adoption_id",
					source_id: this.obj["pet_adoption_id"],
					user_id
				};

				var _this = this;

				// 点赞状态
				if (this.state_praise) {
					this.state_praise = false;
					this.$get('~/api/praise/del?', query, (res) => {
						if(res.result){
							var praise_len = _this.praiseLen = _this.praiseLen-1
							this.$post('~/api/pet_adoption/set?pet_adoption_id=' + _this.obj["pet_adoption_id"], {
								praise_len
							}, (res) => {
								if(res.result){
									console.log("添加点赞数状态：" ,res.result);
								}
								else if(res.error){
									console.error(res.error);
								}
							});
							// this.$toast("取消点赞");
							this.$message.success("取消点赞")
						}
						else if (res.error){
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				} else {
					this.state_praise = true;
					this.$post('~/api/praise/add?', query, (res) => {
						if (res.result) {
							var praise_len = _this.praiseLen = _this.praiseLen+1
							this.$post('~/api/pet_adoption/set?pet_adoption_id=' + _this.obj["pet_adoption_id"], {
								praise_len
							}, (res) => {
								if(res.result){
									console.log("添加点赞数状态：" ,res.result);
								}
								else if(res.error){
									console.error(res.error);
								}
							});
							// this.$toast("点赞成功");
							this.$message.success("点赞成功")
						}
						else if (res.error) {
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				};
			},
				/**
			 * 改变收藏状态
			 */
			change_collect() {
				var user_id = this.user.user_id;

				var query = {
					source_table: "pet_adoption",
					source_field: "pet_adoption_id",
					source_id: this.obj["pet_adoption_id"],
					user_id
				};

				// 收藏状态
				if (this.state_collect) {
					this.state_collect = false;
					this.$get('~/api/collect/del?', query, (res) => {
						if(res.result){
							// this.$toast("取消收藏");
							this.$message.success("取消收藏")
						}
						else if (res.error){
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				} else {
					this.state_collect = true;
					this.$post('~/api/collect/add?', query, (res) => {
						if (res.result) {
							// this.$toast("收藏成功");
							this.$message.success("收藏成功")
						}
						else if (res.error) {
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				};
			},
		},
		created() {
			this.get_list_user_publisher_number();
			this.get_list_user_adopter_number();
		},
		watch:{
			obj:{
				deep: true,
				handler(){
					this.praiseLen = this.obj["praise_len"]
				}
			}
		},
	};
</script>

<style>
/* .diy_details{
	width: 1140px;
	margin: 0 auto 50px;
	background: #fff;
	box-sizing: border-box;
	padding: 30px;
}
.details_title{
	width: 100%;
	height: 50px;
	line-height: 50px;
	background: rgb(4, 126, 134);
	font-size: 22px;
	color: #fff;
	margin-bottom: 40px;
	padding-left: 15px;
	overflow: hidden;
}
	.row_top_wrap{
		display: flex;
		flex-direction: row;
		margin-bottom: 45px;
		min-height: 225px;
	}
.row_top_wrap .row_1{
	width: 300px;
	display: flex;
	flex-direction: column;
	margin-right: 20px;
}
.row_top_wrap .row_1 .diy_img,
.row_top_wrap .row_1 .diy_img img{
	width: 100%;
	height: 100%;
	border-radius: 15px;
	margin-bottom: 4px;
	box-shadow: 0 2px 16px 2px #ccc;
}
.diy_details_box .warp .container .row{
	justify-content: start;
}
.row_top_wrap .row_2{
	width: 840px;
}
.diy_details_box .warp .container,
.diy_details_box .warp .container .row{
	padding: 0;
	margin: 0;
}
.row_top_wrap .row_2 .view{
	display: flex;
	margin-bottom: 15px;
}
.row_top_wrap .row_2 .view .diy_title{
	width: 100px;
	text-align: right;
	font-size: 20px;
	height: 32px;
	line-height: 32px;
}
.row_top_wrap .row_2 .view .diy_field{
	margin-left: 15px;
	font-size: 18px;
}
.rich_text{
	margin-bottom: 25px;
	border-bottom: 1px solid #ccc;
	padding-top: 25px;
	display: block;
	width: 100%;
	padding-bottom: 25px;
}
.rich_text .diy_title{
	font-size: 22px;
	height: 24px;
	line-height: 24px;
	padding-left: 10px;
	border-left: 6px solid rgb(5, 141, 130);
	margin-bottom: 25px;
	
}
.rich_text .diy_html h1,
.rich_text .diy_html h2,
.rich_text .diy_html h3{
	font-size: 18px!important;
	color: #333!important;
	font-weight: bold!important;
	margin-bottom: 15px!important;
}
.rich_text .diy_html p,
.rich_text .diy_html div,
.rich_text .diy_html a,
.rich_text .diy_html span{
	font-size: 16px!important;
	color: #666!important;
}
.rich_text .diy_html img{
	max-width: 100%!important;
}
.praise_wrap{
	margin-top: 45px;
	width: 100%;
	text-align: right;
}
.praise_wrap .diy_title{
	color: #333;
}
.praise_wrap .diy_text{
	
	color: rgba(14, 134, 143, 0.685);
}
.details_btn_wrap{
	text-align: right;
	margin: 30px 0;
	border-top: 1px solid #ccc;
	border-bottom: 2px dashed rgba(14, 134, 143, 0.685);
	padding-top: 40px;
	padding-bottom: 40px;
	width: 100%;
}
.details_btn{
	margin-left: 20px;
	width: 100px;
	height: 40px;
	text-align: center;
	background: rgb(3, 129, 112);
	border-color: rgb(3, 129, 112);
}
.details_btn:hover{
	background: rgb(2, 158, 137);
	border-color: rgb(3, 129, 112);
}
.mt{
	font-size: 22px;
    height: 24px;
    line-height: 24px!important;
    padding-left: 10px;
    border-left: 6px solid rgb(5, 141, 130);
    margin-bottom: 25px;
}
.diy_details .form_editor_block{
	height: 300px;
}
.diy_details .fn .btn_publish{
	margin: 110px 0 30px;
	text-align: center;
}
.diy_details .fn .btn_publish .btn{
	width: 150px;
	display: inline-block;
		background: rgb(2, 158, 137);
	border-color: rgb(3, 129, 112)!important;
	color: #fff!important;
}
.diy_details .fn .btn_publish .btn:hover{
	background: rgb(2, 158, 137);
	border-color: rgb(3, 129, 112);
	
}
.comment_box{
	border-bottom: 2px dashed rgb(3, 129, 112);
	padding-bottom: 30px;
	margin-bottom: 40px;
}
.diy_details .pagination{
	justify-content: center;
	padding-top: 25px;
	border-top: 1px solid #ccc;
}
.form_edit_box .details_form_editor_title{
	    font-size: 22px;
    height: 24px;
    line-height: 24px;
    padding-left: 10px;
    border-left: 6px solid rgb(5, 141, 130);
    margin-bottom: 40px;
	
}
.answer_title{
	font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	font-size: 18px;
	color: rgb(3, 129, 112);
}
.reset_answer_personnel{
	width: 120px;
	height: 40px;
	text-align: center;
	background: rgb(3, 129, 112);
	border-color: rgb(3, 129, 112)!important;
	color: #fff!important;
	font-size: 14px;
	font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	position: relative;
	top: -5px;
	
}
.reset_answer_personnel:hover{
	background: rgb(3, 129, 112);
	border-color: rgb(3, 129, 112)!important;
	
} */
</style>
