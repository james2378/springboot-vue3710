<template>
	<div class="diy_edit page_pet_adoption" id="pet_adoption_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','pet_number') || $check_field('add','pet_number') || $check_field('get','pet_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>宠物编号:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_pet_number" v-model="form['pet_number']" placeholder="请输入宠物编号" v-if="(form['pet_number'] && $check_field('set','pet_number')) || (!form['pet_number'] && $check_field('add','pet_number'))"  :disabled="disabledObj['pet_number_isDisabled']"/>
							<span v-else-if="$check_field('get','pet_number')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','title') || $check_field('add','title') || $check_field('get','title')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>标题:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_title" v-model="form['title']" placeholder="请输入标题" v-if="(form['title'] && $check_field('set','title')) || (!form['title'] && $check_field('add','title'))"  :disabled="disabledObj['title_isDisabled']"/>
							<span v-else-if="$check_field('get','title')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','gender') || $check_field('add','gender') || $check_field('get','gender')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>性别:
							</span>
						</div>
						<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_gender" v-model="form['gender']" v-if="(form['gender'] && $check_field('set','gender')) || (!form['gender'] && $check_field('add','gender'))" >
								<option v-for="o in list_gender" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','gender')">{{ form['gender'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','age') || $check_field('add','age') || $check_field('get','age')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>年龄:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_age" v-model="form['age']" placeholder="请输入年龄" v-if="(form['age'] && $check_field('set','age')) || (!form['age'] && $check_field('add','age'))"  :disabled="disabledObj['age_isDisabled']"/>
							<span v-else-if="$check_field('get','age')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','publisher_number') || $check_field('add','publisher_number') || $check_field('get','publisher_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>发布者编号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_publisher_number" :disabled="disabledObj['publisher_number_isDisabled']" v-model="form['publisher_number']" v-if="(form['publisher_number'] && $check_field('set','publisher_number')) || (!form['publisher_number'] && $check_field('add','publisher_number'))" >
								<option v-for="o in list_user_publisher_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','publisher_number')">{{ form['publisher_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','publisher_name') || $check_field('add','publisher_name') || $check_field('get','publisher_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>发布者姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_publisher_name" v-model="form['publisher_name']" placeholder="请输入发布者姓名" v-if="(form['publisher_name'] && $check_field('set','publisher_name')) || (!form['publisher_name'] && $check_field('add','publisher_name'))"  :disabled="disabledObj['publisher_name_isDisabled']"/>
							<span v-else-if="$check_field('get','publisher_name')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','adopter_number') || $check_field('add','adopter_number') || $check_field('get','adopter_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>领养者编号:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_adopter_number" :disabled="disabledObj['adopter_number_isDisabled']" v-model="form['adopter_number']" v-if="(form['adopter_number'] && $check_field('set','adopter_number')) || (!form['adopter_number'] && $check_field('add','adopter_number'))" >
								<option v-for="o in list_user_adopter_number" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','adopter_number')">{{ form['adopter_number'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','adopter_name') || $check_field('add','adopter_name') || $check_field('get','adopter_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>领养者姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_adopter_name" v-model="form['adopter_name']" placeholder="请输入领养者姓名" v-if="(form['adopter_name'] && $check_field('set','adopter_name')) || (!form['adopter_name'] && $check_field('add','adopter_name'))"  :disabled="disabledObj['adopter_name_isDisabled']"/>
							<span v-else-if="$check_field('get','adopter_name')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','adopter_telephone') || $check_field('add','adopter_telephone') || $check_field('get','adopter_telephone')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>领养者电话:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_adopter_telephone" v-model="form['adopter_telephone']" placeholder="请输入领养者电话" v-if="(form['adopter_telephone'] && $check_field('set','adopter_telephone')) || (!form['adopter_telephone'] && $check_field('add','adopter_telephone'))"  :disabled="disabledObj['adopter_telephone_isDisabled']"/>
							<span v-else-if="$check_field('get','adopter_telephone')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','adoption_instructions') || $check_field('add','adoption_instructions') || $check_field('get','adoption_instructions')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>领养说明:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_adoption_instructions" v-model="form['adoption_instructions']" v-if="(form['adoption_instructions'] && $check_field('set','adoption_instructions')) || (!form['adoption_instructions'] && $check_field('add','adoption_instructions'))" :disabled="disabledObj['adoption_instructions_isDisabled']" />
							<span v-else-if="$check_field('get','adoption_instructions')">{{ form['publisher_name'] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/pet_adoption/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/pet_adoption/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/pet_adoption/get_obj?",
				url_add: "~/api/pet_adoption/add?",
				url_set: "~/api/pet_adoption/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"pet_number": "",
					"title": "",
					"gender": "",
					"age": "",
					"publisher_number": 0,
					"publisher_name": "",
					"adopter_number": 0,
					"adopter_name": "",
					"adopter_telephone": "",
					"adoption_instructions": "",
					"pet_adoption_id": 0,
				},

				obj: {
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
					"pet_adoption_id": 0,
				},

				// 表单字段
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
					"pet_adoption_id": 0,
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

				// ID字段
				field: "pet_adoption_id",
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
			async get_user_session_adopter_number(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=领养者");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["adopter_number"] = user_id
								_this.disabledObj['adopter_number' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "adopter_number") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/pet_adoption/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
          delete(form.examine_state)
          delete(form.examine_reply)
          this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (obj) {
          delete(obj.examine_state)
          delete(obj.examine_reply)
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
          delete(form.examine_state)
          delete(form.examine_reply)
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_list_user_publisher_number();
			this.get_user_session_adopter_number();
			this.get_list_user_adopter_number();
		},
	}
</script>

<style>
</style>
