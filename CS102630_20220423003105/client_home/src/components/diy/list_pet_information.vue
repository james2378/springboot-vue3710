<template>
	<div class="diy_home diy_list diy_pet_information" id="diy_pet_information_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/pet_information/details?pet_information_id=' + o['pet_information_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/pet_information/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/pet_information/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','pet_number')">宠物编号
					</th>
					<th class="diy_title" v-if="$check_field('get','title')">标题
					</th>
					<th class="diy_title" v-if="$check_field('get','pet_variety')">宠物品种
					</th>
					<th class="diy_title" v-if="$check_field('get','photo')">照片
					</th>
					<th class="diy_title" v-if="$check_field('get','gender')">性别
					</th>
					<th class="diy_title" v-if="$check_field('get','age')">年龄
					</th>
					<th class="diy_title" v-if="$check_field('get','sterilization')">是否绝育
					</th>
					<th class="diy_title" v-if="$check_field('get','publisher_number')">发布者编号
					</th>
					<th class="diy_title" v-if="$check_field('get','publisher_name')">发布者姓名
					</th>
					<th class="diy_title" v-if="$check_field('get','vaccination_status')">疫苗接种情况
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','pet_number')">
						<span>
							{{ o["pet_number"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','title')">
						<span>
							{{ o["title"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','pet_variety')">
						<span>
							{{ o["pet_variety"] }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','photo')">
						<img class="diy_img" :src="o['photo']" />
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','gender')">
						<span>
							{{ o["gender"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','age')">
						<span>
							{{ o["age"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','sterilization')">
						<span>
							{{ o["sterilization"] }}
						</span>
					</td>
					<td class="diy_field diy_uid" v-if="$check_field('get','publisher_number')">
						<span>
							{{ get_user_name(o['publisher_number']) }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','publisher_name')">
						<span>
							{{ o["publisher_name"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','vaccination_status')">
						<span>
							{{ o["vaccination_status"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "照片",
							name: "photo",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "宠物编号",
									name: "pet_number",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "标题",
									name: "title",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "宠物品种",
									name: "pet_variety",
									type: "下寻",
									is_img_list: "1"
								},
								{
									title: "性别",
									name: "gender",
									type: "下拉",
									is_img_list: "1"
								},
								{
									title: "年龄",
									name: "age",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "是否绝育",
									name: "sterilization",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "发布者编号",
									name: "publisher_number",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "发布者姓名",
									name: "publisher_name",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "疫苗接种情况",
									name: "vaccination_status",
									type: "多文本"
								},
								{
									title: "详情",
									name: "details",
									type: "编辑"
								},
						],
				// 用户列表
				list_user_publisher_number: [],
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
			get_user_name(id){
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
		},
		created() {
			this.get_list_user_publisher_number();
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

