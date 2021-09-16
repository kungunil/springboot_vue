<template>
  <div id="home">

    <div>
      <el-button @click="add">新增</el-button>
      <el-button>导入</el-button>
      <el-button>导出</el-button>
    </div>
    <div>
      <el-input v-model="search" type="text" style="width: 50%" clearable placeholder="search........."></el-input>
      <el-button style="margin: 10px" type="primary" icon="el-icon-search" @click="load">查询</el-button>
    </div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          prop="id"
          label="ID"
          width="180"
          sortable>
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="password"
          label="密码">
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄">
      </el-table-column>
      <el-table-column
          prop="sex"
          label="性别">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
      <el-table-column

          label="操作"
          width="100">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？"
          @confirm="handleDelete(scope.row.id)"
          >
            <template #reference>
              <el-button type="text" size="small" >删除</el-button>
            </template>
          </el-popconfirm>

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
    <el-dialog
        title="提示"
        v-model="dialogVisible"
        width="30%">
      <el-form :model="form"  label-width="120px">
        <el-form-item label="用户名" style="width: 80%">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" style="width: 80%">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="年龄" style="width: 80%">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" >
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
          <el-radio v-model="form.sex" label="秀吉">秀吉</el-radio>
        </el-form-item>
        <el-form-item  label="地址" style="width: 80%">
          <el-input type="textarea " v-model="form.address"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="save ">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      form: {},
      dialogVisible: false,
      search:'',
      pageSize: 10,
      currentPage: 1,
      total: 0,
      tableData: [

      ]
    }
  },
  created() {
    this.load();
  },
  methods: {

    load(){
      request.get("/user",{
       params: {
         pageNum: this.currentPage,
         pageSize: this.pageSize,
         search: this.search
       }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total= res.data.total
        this.currentPage=res.data.current
      })
    },

    add(){
      this.dialogVisible=true;
      this.form = {};

    },
    save(){
      if (this.form.id){
        this.dialogVisible=false
        request.put("/user",this.form).then(res =>{
          console.log(res)
          if (res.code ==200){
            this.$message({
              type: "success",
              message: "更新成功"
            })
            this.load()
          }else {
            this.$message({
              type: "fail",
              message: "更新失败"
            })
          }
        })
      }else {
        this.dialogVisible=false
        request.post("/user",this.form).then(res =>{
          console.log(res)
          if (res.code ==200){
            this.$message({
              type: "success",
              message: "新增成功"
            })
            this.load()
          }else {
            this.$message({
              type: "fail",
              message: "新增失败"
            })
          }
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    handleDelete(id){
        request.delete("/user/"+id,{
          }).then(res =>{
            if (res.code==200){
              this.$message({
                type: "success",
                message: "删除成功"
              })
              this.load()
            }else {
              this.$message({
                type: "fail",
                message: "删除失败"
              })
            }
        })

    },

    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.currentPage=pageNum
      this.load()
    }
  }
}
</script>

<style>

</style>
