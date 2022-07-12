<!-- eslint-disable vue/html-self-closing -->
<template>
  <div class="main">
    <div class="btn">
      <div class="serach">
        <el-input v-model="phone" placeholder="请输入手机号" width="200" />
      </div>

      <el-button type="primary" icon="el-icon-search" @click="search"
        >搜索</el-button
      >
      <el-button type="primary" @click="searchAll">全部</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="addAccount"
        >添加</el-button
      >
    </div>

    <!-- 查询账户数据列表 -->
    <el-table ref="singleTable" :data="tableData" border>
      <el-table-column type="index" label="序号" width="50"></el-table-column>
      <el-table-column
        prop="account"
        label="账户（登录账号）"
      ></el-table-column>
      <el-table-column prop="nickName" label="昵称"></el-table-column>

      <el-table-column label="头像">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" alt="" width="50px" height="50px" />
        </template>
      </el-table-column>
      <el-table-column prop="accountType" label="账户类型">
        <template slot-scope="scope">
          {{ scope.row.accountType == 10 ? "普通管理员" : "超管" }}
        </template>
      </el-table-column>
      <el-table-column prop="gmtCreate" label="注册时间"></el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            :type="scope.row.useStatus == 1 ? 'danger' : 'success'"
            size="mini"
            @click="useStatus(scope.row.id)"
          >
            {{ scope.row.useStatus == 1 ? "禁用" : "启用" }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="block">
      <el-pagination
        :current-page="current"
        :page-sizes="[10, 30, 50]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>

    <!-- 创建新的账户 -->
    <el-dialog title="创建新的账户" :visible.sync="dialogFormVisible">
      <el-form :model="dateFrom" :rules="rules" ref="addAccount">
        <el-form-item label="账号" prop="account">
          <el-input v-model="dateFrom.account" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="dateFrom.nickName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="avatar">
          <el-upload
            ref="productFileidTwo"
            :file-list="logofileListTwo"
            :headers="headers"
            :action="logoAction"
            :on-success="handleAvatarSuccess"
            :on-remove="removedetal"
            list-type="picture-card"
            accept="image/*"
            :limit="1"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="onCancel">取 消</el-button>
        <el-button type="primary" @click="createAccount('addAccount')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  accountListApi,
  updateAccountUseStatusApi,
  createAccountApi,
  uploadImgApi,
  delImgApi,
} from "@/api/account";
import { getToken } from "@/utils/auth";
export default {
  data() {
    return {
      size: 10,
      current: 1,
      total: 0,
      tableData: [],
      logofileListTwo: [],
      headers: { token: getToken() },
      phone: undefined,
      showup: false,
      dialogFormVisible: false,
      dateFrom: {
        account: undefined, // 账号
        nickName: undefined, // 昵称
        avatar: undefined, // 头像
      },
      logoAction: process.env.VUE_APP_BASE_API + "/oss-do",
      imageUrl: "",
      rules: {
        account: [{ required: true, message: "输入不能为空", trigger: "blur" }],
        nickName: [
          { required: true, message: "输入不能为空", trigger: "blur" },
        ],
        avatar: [{ required: true, message: "请上传图片", trigger: "blur" }],
      },
    };
  },
  // 不能删除
  created() {
    this.getlist();
  },
  methods: {
    search() {
      this.current = 1;
      this.getlist();
    },
    searchAll() {
      this.phone = undefined;
      this.current = 1;
      this.getlist();
    },
    addAccount() {
      this.dialogFormVisible = true;
    },
    // 分页
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val;
      this.getlist();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val;
      this.getlist();
    },
    getlist() {
      accountListApi({
        account: this.phone,
        pageCurrent: this.current,
        pageSize: this.size,
      }).then((res) => {
        if (res.code === 200) {
          this.total = res.result.totalCount;
          this.tableData = res.result.list;
        }
      });
    },
    useStatus(id) {
      updateAccountUseStatusApi(id).then((res) => {
        if (res.code === 200) {
          this.$message({
            message: res.message,
            type: res.success,
          });
          this.getlist();
        }
      });
    },
    onCancel() {
      this.dialogFormVisible = false;
      this.$message({
        message: "取消",
        type: "warning",
      });
      this.getlist();
    },
    // 上传图片
    handleAvatarSuccess(res, file) {
      this.dateFrom.avatar = res.result;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
    },
    // 删除图片地址
    removedetal(file) {
      const filePath = file.response.result;
      console.log("删除的图片地址为：" + filePath);
      delImgApi(filePath).then((res) => {
        if (res.code === 200) {
          this.$message({
            message: res.message,
            type: res.success,
          });
          this.getlist();
        }
      });
    },
    // 创建
    createAccount(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.dialogFormVisible = false;
          createAccountApi(this.dateFrom).then((res) => {
            if (res.code === 200) {
              this.$message({
                message: res.message,
                type: res.success,
              });
              this.getlist();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.btn {
  margin: 20px 0;
  display: flex;
}
.serach {
  width: 300px;
  margin-right: 20px !important;
}
.main >>> .el-table th {
  text-align: center;
}
.main >>> .el-table td,
.el-table th.is-leaf {
  text-align: center !important;
}
.main {
  padding: 30px;
  background-color: #f5f5f5;
}
.btn {
  margin: 20px 0;
}
.block {
  padding: 20px;
  background-color: #fff;
  /* margin-top: 20px; */
  /* margin: 40px auto; */
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
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
