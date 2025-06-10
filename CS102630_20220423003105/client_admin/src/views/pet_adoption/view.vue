<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','pet_number') || $check_field('add','pet_number') || $check_field('set','pet_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="宠物编号" prop="pet_number">
					<el-input id="pet_number" v-model="form['pet_number']" placeholder="请输入宠物编号"
							  v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','pet_number')) || (!form['pet_adoption_id'] && $check_field('add','pet_number'))" :disabled="disabledObj['pet_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','pet_number')">{{form['pet_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','title') || $check_field('add','title') || $check_field('set','title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题" prop="title">
					<el-input id="title" v-model="form['title']" placeholder="请输入标题"
							  v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','title')) || (!form['pet_adoption_id'] && $check_field('add','title'))" :disabled="disabledObj['title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title')">{{form['title']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gender') || $check_field('add','gender') || $check_field('set','gender')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="性别" prop="gender">
					<el-select id="gender" v-model="form['gender']"
						v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','gender')) || (!form['pet_adoption_id'] && $check_field('add','gender'))">
						<el-option v-for="o in list_gender" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','gender')">{{form['gender']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','age') || $check_field('add','age') || $check_field('set','age')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="年龄" prop="age">
					<el-input id="age" v-model="form['age']" placeholder="请输入年龄"
							  v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','age')) || (!form['pet_adoption_id'] && $check_field('add','age'))" :disabled="disabledObj['age_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','age')">{{form['age']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publisher_number') || $check_field('add','publisher_number') || $check_field('set','publisher_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布者编号" prop="publisher_number">
						<el-select v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','publisher_number')) || (!form['pet_adoption_id'] && $check_field('add','publisher_number'))" id="publisher_number" v-model="form['publisher_number']" :disabled="disabledObj['publisher_number_isDisabled']">
							<el-option v-for="o in list_user_publisher_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','publisher_number')" id="publisher_number" v-model="form['publisher_number']" :disabled="true">
							<el-option v-for="o in list_user_publisher_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','publisher_name') || $check_field('add','publisher_name') || $check_field('set','publisher_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="发布者姓名" prop="publisher_name">
					<el-input id="publisher_name" v-model="form['publisher_name']" placeholder="请输入发布者姓名"
							  v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','publisher_name')) || (!form['pet_adoption_id'] && $check_field('add','publisher_name'))" :disabled="disabledObj['publisher_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','publisher_name')">{{form['publisher_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adopter_number') || $check_field('add','adopter_number') || $check_field('set','adopter_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="领养者编号" prop="adopter_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_adopter_number(form['adopter_number']) }}
							<!--<el-input id="business_name" v-model="form['adopter_number']" placeholder="请输入领养者编号"-->
							<!--v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','adopter_number')) || (!form['pet_adoption_id'] && $check_field('add','adopter_number'))" :disabled="disabledObj['adopter_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','adopter_number')">{{form['adopter_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','adopter_number')) || (!form['pet_adoption_id'] && $check_field('add','adopter_number'))" id="adopter_number" v-model="form['adopter_number']" :disabled="disabledObj['adopter_number_isDisabled']">
								<el-option v-for="o in list_user_adopter_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','adopter_number')" id="adopter_number" v-model="form['adopter_number']" :disabled="true">
								<el-option v-for="o in list_user_adopter_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="adopter_number" v-model="form['adopter_number']" :disabled="disabledObj['adopter_number_isDisabled']">
							<el-option v-for="o in list_user_adopter_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adopter_name') || $check_field('add','adopter_name') || $check_field('set','adopter_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="领养者姓名" prop="adopter_name">
					<el-input id="adopter_name" v-model="form['adopter_name']" placeholder="请输入领养者姓名"
							  v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','adopter_name')) || (!form['pet_adoption_id'] && $check_field('add','adopter_name'))" :disabled="disabledObj['adopter_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','adopter_name')">{{form['adopter_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adopter_telephone') || $check_field('add','adopter_telephone') || $check_field('set','adopter_telephone')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="领养者电话" prop="adopter_telephone">
					<el-input id="adopter_telephone" v-model="form['adopter_telephone']" placeholder="请输入领养者电话"
							  v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','adopter_telephone')) || (!form['pet_adoption_id'] && $check_field('add','adopter_telephone'))" :disabled="disabledObj['adopter_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','adopter_telephone')">{{form['adopter_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','adoption_instructions') || $check_field('add','adoption_instructions') || $check_field('set','adoption_instructions')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="领养说明" prop="adoption_instructions">
					<el-input type="textarea" id="adoption_instructions" v-model="form['adoption_instructions']" placeholder="请输入领养说明"
						v-if="user_group === '管理员' || (form['pet_adoption_id'] && $check_field('set','adoption_instructions')) || (!form['pet_adoption_id'] && $check_field('add','adoption_instructions'))" :disabled="disabledObj['adoption_instructions_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','adoption_instructions')">{{form['adoption_instructions']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else>{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else>{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "pet_adoption_id",
				url_add: "~/api/pet_adoption/add?",
				url_set: "~/api/pet_adoption/set?",
				url_get_obj: "~/api/pet_adoption/get_obj?",
				url_upload: "~/api/pet_adoption/upload?",

				query: {
					"pet_adoption_id": 0,
				},

				form: {
					"pet_number":'', // 宠物编号
					"title":'', // 标题
					"gender":'', // 性别
					"age":'', // 年龄
					"publisher_number": 0, // 发布者编号
					"publisher_name":'', // 发布者姓名
					"adopter_number": 0, // 领养者编号
					"adopter_name":'', // 领养者姓名
					"adopter_telephone":'', // 领养者电话
					"adoption_instructions":'', // 领养说明
					"examine_state": "未审核",
					"examine_reply": "",
					"pet_adoption_id": 0, // ID

				},
				disabledObj:{
					"pet_number_isDisabled": false,
					"title_isDisabled": false,
					"gender_isDisabled": false,
					"age_isDisabled": false,
					"publisher_number_isDisabled": false,
					"publisher_name_isDisabled": false,
					"adopter_number_isDisabled": false,
					"adopter_name_isDisabled": false,
					"adopter_telephone_isDisabled": false,
					"adoption_instructions_isDisabled": false,
				},
				//性别选项列表
				list_gender: ['公','母'],
				// 用户列表
				list_user_publisher_number: [],
				// 用户列表
				list_user_adopter_number: [],
				// 用户组
				group_user_adopter_number: "",
			}
		},
		methods: {
			/**
			 * 获取发布者用户列表
			 */
			async get_list_user_publisher_number() {
                // if(this.user_group !== "管理员" && this.form["publisher_number"] === 0) {
                //     this.form["publisher_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=发布者");
                if(json.result && json.result.list){
                    this.list_user_publisher_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_publisher_number(id){
				var obj = this.list_user_publisher_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取领养者用户列表
			 */
			async get_list_user_adopter_number() {
                // if(this.user_group !== "管理员" && this.form["adopter_number"] === 0) {
                //     this.form["adopter_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=领养者");
                if(json.result && json.result.list){
                    this.list_user_adopter_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取领养者用户组
			 */
			async get_group_user_adopter_number() {
				this.form["adopter_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=领养者");
				if(json.result && json.result.obj){
					this.group_user_adopter_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_adopter_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_adopter_number.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                for (var j = 0; j < arrForm.length; j++) {
                  if (arr[i] === arrForm[j]) {
                    if (arr[i] !== "adopter_number") {
                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                      break;
                    } else {
                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                    }
                  }
                }
              }
						}
					}
				});
			},
			get_user_adopter_number(id){
				var obj = this.list_user_adopter_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "发布者编号":
							if(param["publisher_number"] > 0){
								param["publisher_number"] = this.user.user_id;
							}
							break;
						case "领养者编号":
							if(param["adopter_number"] > 0){
								param["adopter_number"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/pet_adoption/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/pet_adoption/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/pet_adoption/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/pet_adoption/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/pet_adoption/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_publisher_number();
			this.get_list_user_adopter_number();
			this.get_group_user_adopter_number();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
