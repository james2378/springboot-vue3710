<template>
	<div class="diy_list page_pet_information" id="pet_information_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">宠物信息列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="标题搜索" v-model="query['title']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="宠物品种搜索" v-model="query['pet_variety']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="宠物品种" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','pet_variety')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_pet_variety" :key="i" @click="filter_set(o['pet_variety'],'pet_variety')" >
												{{ o['pet_variety'] }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_pet_information :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_pet_information from "@/components/diy/list_pet_information.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_pet_information
		},
		data() {
			return {
				url_get_list: "~/api/pet_information/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"title": "", // 标题
					"pet_variety": "", // 宠物品种
					"examine_state": "已通过", // 审核状态
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "create_time desc",
					},
					{
						name: "创建时间从低到高",
						value: "create_time asc",
					},
					{
						name: "更新时间从高到低",
						value: "update_time desc",
					},
					{
						name: "更新时间从低到高",
						value: "update_time asc",
					},
					{
						name: "标题正序",
						value: "title asc",
					},
					{
						name: "标题倒序",
						value: "title desc",
					},
					{
						name: "宠物品种正序",
						value: "pet_variety asc",
					},
					{
						name: "宠物品种倒序",
						value: "pet_variety desc",
					},
				],
				// 宠物品种列表
				"list_pet_variety": [],
				// 性别列表
				"list_gender": ['公','母'],
				// 是否绝育列表
				"list_sterilization": ['是','否'],
			}
		},
		methods: {
      get_list_before(param) {
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取宠物品种列表
			 */
			async get_list_pet_variety() {
				var json = await this.$get("~/api/pet_classification/get_list?");
				if (json.result) {
					this.list_pet_variety = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_pet_variety(o) {
				if (o == "全部") {
					this.query["pet_variety"] = "";
				} else {
					this.query["pet_variety"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.title = ""
				this.query.pet_variety = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },
		},
		computed: {
		},
		created() {
			/**
			 * 获取宠物品种列表
			 */
			this.get_list_pet_variety();
		}
	}
</script>

<style>
</style>
