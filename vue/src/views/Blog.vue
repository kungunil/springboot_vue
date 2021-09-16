<template>
  <div id="blog">

    <div >
      <el-button @click="add">新增</el-button>
      <el-button>导入</el-button>
      <el-button>导出</el-button>
    </div>
    <div style="padding: 1em">
      <el-input
          placeholder="请输入内容"
          style="width: 50%"
          v-model="search"
          clearable>
      </el-input>
      <el-button icon="el-icon-search" @click="load" circle></el-button>
    </div>
    <div  style="width:80em!important;">
      <el-table
          :data="tableData"
          border
          >
        <el-table-column
            prop="id"
            label="ID"

            sortable>
        </el-table-column>
        <el-table-column
            prop="title"
            label="标题"

        >
        </el-table-column>
        <el-table-column
            prop="picture"
            label="图片"

        >
        </el-table-column>
        <el-table-column
            prop="content"
            label="内容"
            >
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="发表时间"

            sortable>
        </el-table-column>
        <el-table-column
            prop="updateTime"
            label="更新时间"

            sortable>
        </el-table-column>
        <el-table-column
            label="操作"

        >
          <template #default="scope">
            <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
            <el-button type="text" @click="deleteBlog(scope.row.id)" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>

  <el-dialog
      title="提示"
      v-model="dialogVisible"
      width="30%"
  >
    <el-form :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
      <el-form-item label="博客标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="图片" >
        <el-upload
            class="upload-demo"
            action="http://localhost:8081/files/upload" :on-success="fileUploadSuccess"
        >
          <el-button  type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <el-input v-model="ruleForm.content"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="blog_input">立即创建</el-button>
        <el-button @click="add">重置</el-button>
      </el-form-item>
    </el-form>
    <template #footer>
    </template>
  </el-dialog>
</template>

<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      tableData: [],
      search: '',
      pageSize: 10,
      currentPage: 1,
      total: 0,
      dialogVisible: false,
      ruleForm: {
      },
      rules: {
        title: [{
          required:true,message: '请输入标题', trigger:'blur'
        }],
        content: [{
          required:true,message: '请输入内容', trigger:'blur'
        }]
      }
    }
  },
  created() {
      this.load()
  },

  methods: {
    fileUploadSuccess(res){
      console.log(res)
      this.ruleForm.picture=res.data
    },
    handleEdit(row){
     this.ruleForm = JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    deleteBlog(id){
      request.delete("/blog/"+id).then(res => {
        if (res.code==200){
          ElMessage({
            showClose: true,
            message: '删除成功',
            type: 'success'

          });
          this.load()
        }  else {
          ElMessage.error('删除失败');
        }
      })
    },
    blog_input(){
      if (this.ruleForm.id){
        request.put("/blog",this.ruleForm).then(res => {
          if (res.code==200){
            ElMessage({
              showClose: true,
              message: '编辑成功',
              type: 'success'
            });
            this.dialogVisible=false
            this.load()
          }else {
            ElMessage.error('编辑失败');
          }
        })
      }else {
        request.post("/blog",this.ruleForm).then(res => {
          if (res.code==200){
            ElMessage({
              showClose: true,
              message: '新增成功',
              type: 'success'
            });
            this.dialogVisible=false
            this.load()
          }else {
            ElMessage.error('新增失败');
          }
        })

      }
    },
    add(){
      this.dialogVisible=true
      this.ruleForm={}
    },
   load(){
     request.get("/blog",{
       params:{
         pageNum: this.currentPage,
         pageSize: this.pageSize,
         search: this.search
       }
     }).then(res => {
       this.tableData = res.data.records
       this.total=res.data.total
       this.currentPage=res.data.currentPage
     })
   },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
     this.currentPage=pageNum
      this.load()
    }

  },

}
</script>

<style>

</style>
