<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="宠物资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/pet_classification/list', 'get')"
				:list="result_pet_classification_pet_variety"
				title="宠物分类宠物品种"
				source_table="pet_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/adopter/list', 'get')"
				:list="result_adopter_adopter_number"
				title="领养者领养者编号"
				source_table="adopter"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/publisher/list', 'get')"
				:list="result_publisher_publisher_number"
				title="发布者发布者编号"
				source_table="publisher"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/pet_information/list', 'get')"
				:list="result_pet_information_title"
				title="宠物信息标题"
				source_table="pet_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/pet_information/list', 'get')"
				:list="result_pet_information_pet_variety"
				title="宠物信息宠物品种"
				source_table="pet_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/pet_adoption/list', 'get')"
				:list="result_pet_adoption_pet_number"
				title="宠物领养宠物编号"
				source_table="pet_adoption"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/pet_adoption/list', 'get')"
				:list="result_pet_adoption_title"
				title="宠物领养标题"
				source_table="pet_adoption"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/pet_knowledge/list', 'get')"
				:list="result_pet_knowledge_title"
				title="宠物知识标题"
				source_table="pet_knowledge"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_pet_classification_pet_variety":[],
			"result_adopter_adopter_number":[],
			"result_publisher_publisher_number":[],
			"result_pet_information_title":[],
			"result_pet_information_pet_variety":[],
			"result_pet_adoption_pet_number":[],
			"result_pet_adoption_title":[],
			"result_pet_knowledge_title":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取pet_variety
	 */
	get_pet_classification_pet_variety(){
		this.$get("~/api/pet_classification/get_list?like=0", { page: 1, size: 10, "pet_variety": this.query.word }, (json) => {
		  if (json.result) {
			var result_pet_classification_pet_variety = json.result.list;
			result_pet_classification_pet_variety.map(o => o.title = o['pet_variety'])
	  			this.result_pet_classification_pet_variety = result_pet_classification_pet_variety
		 	}
		});
	},
	/**
	 * 获取adopter_number
	 */
	get_adopter_adopter_number(){
		this.$get("~/api/adopter/get_list?like=0", { page: 1, size: 10, "adopter_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_adopter_adopter_number = json.result.list;
			result_adopter_adopter_number.map(o => o.title = o['adopter_number'])
	  			this.result_adopter_adopter_number = result_adopter_adopter_number
		 	}
		});
	},
	/**
	 * 获取publisher_number
	 */
	get_publisher_publisher_number(){
		this.$get("~/api/publisher/get_list?like=0", { page: 1, size: 10, "publisher_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_publisher_publisher_number = json.result.list;
			result_publisher_publisher_number.map(o => o.title = o['publisher_number'])
	  			this.result_publisher_publisher_number = result_publisher_publisher_number
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_pet_information_title(){
		this.$get("~/api/pet_information/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_pet_information_title = json.result.list;
			result_pet_information_title.map(o => o.title = o['title'])
	  			this.result_pet_information_title = result_pet_information_title
		 	}
		});
	},
	/**
	 * 获取pet_variety
	 */
	get_pet_information_pet_variety(){
		this.$get("~/api/pet_information/get_list?like=0", { page: 1, size: 10, "pet_variety": this.query.word }, (json) => {
		  if (json.result) {
			var result_pet_information_pet_variety = json.result.list;
			result_pet_information_pet_variety.map(o => o.title = o['pet_variety'])
	  			this.result_pet_information_pet_variety = result_pet_information_pet_variety
		 	}
		});
	},
	/**
	 * 获取pet_number
	 */
	get_pet_adoption_pet_number(){
		this.$get("~/api/pet_adoption/get_list?like=0", { page: 1, size: 10, "pet_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_pet_adoption_pet_number = json.result.list;
			result_pet_adoption_pet_number.map(o => o.title = o['pet_number'])
	  			this.result_pet_adoption_pet_number = result_pet_adoption_pet_number
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_pet_adoption_title(){
		this.$get("~/api/pet_adoption/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_pet_adoption_title = json.result.list;
			result_pet_adoption_title.map(o => o.title = o['title'])
	  			this.result_pet_adoption_title = result_pet_adoption_title
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_pet_knowledge_title(){
		this.$get("~/api/pet_knowledge/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_pet_knowledge_title = json.result.list;
			result_pet_knowledge_title.map(o => o.title = o['title'])
	  			this.result_pet_knowledge_title = result_pet_knowledge_title
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_pet_classification_pet_variety();
		this.get_adopter_adopter_number();
		this.get_publisher_publisher_number();
		this.get_pet_information_title();
		this.get_pet_information_pet_variety();
		this.get_pet_adoption_pet_number();
		this.get_pet_adoption_title();
		this.get_pet_knowledge_title();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_pet_classification_pet_variety();
	  this.get_adopter_adopter_number();
	  this.get_publisher_publisher_number();
	  this.get_pet_information_title();
	  this.get_pet_information_pet_variety();
	  this.get_pet_adoption_pet_number();
	  this.get_pet_adoption_title();
	  this.get_pet_knowledge_title();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
