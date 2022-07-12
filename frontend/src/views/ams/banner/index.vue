<template>
  <div class="main">
    <div class="btn">
      <div class="serach">
        <el-input v-model="title" placeholder="请输入标题" width="200" />
      </div>
      <el-button type="primary" icon="el-icon-search" @click="search"
        >搜索</el-button
      >
      <el-button type="primary" @click="searchAll">全部</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="addBanner"
        >添加</el-button
      >
    </div>

    <!-- 查询账户数据列表 -->
    <el-table ref="singleTable" :data="tableData" border>
      <el-table-column type="index" label="序号" width="100"> </el-table-column>

      <el-table-column prop="title" label="标题"></el-table-column>

      <el-table-column label="图片索引">
        <template slot-scope="scope">
          <img :src="scope.row.bannerUrl" alt="" width="100px" height="50px" />
        </template>
      </el-table-column>

      <el-table-column label="路径类型">
        <template slot-scope="scope">
          {{
            scope.row.pathType == 0
              ? "否"
              : scope.row.pathType == 1
              ? "商品"
              : scope.row.pathType == 2
              ? "APP页面"
              : scope.row.pathType == 3
              ? "外部地址"
              : ""
          }}
        </template>
      </el-table-column>
      <el-table-column prop="path" label="跳转路径"></el-table-column>

      <el-table-column prop="activityStatus" label="活动状态">
        <template slot-scope="scope">
          {{
            scope.row.activityStatus == 0
              ? "否"
              : scope.row.activityStatus == 1
              ? "活动开始"
              : scope.row.activityStatus == 2
              ? "活动结束"
              : ""
          }}
        </template>
      </el-table-column>

      <el-table-column
        prop="activityBeginTime"
        label="活动开始时间"
      ></el-table-column>
      <el-table-column
        prop="activityEndTime"
        label="活动结束时间"
      ></el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="success" size="mini" @click="updateById(scope.row)">
            修改
          </el-button>
          <el-button
            type="danger"
            size="mini"
            @click="removeBanner(scope.row.id)"
            >删除</el-button
          >
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

    <!-- 创建 -->
    <el-dialog title="创建" :visible.sync="dialogFormVisible">
      <el-form :model="dateFrom" :rules="rules" ref="addBanner">
        <el-form-item label="标题" prop="title">
          <el-input v-model="dateFrom.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Banner地址" prop="bannerUrl">
          <el-upload
            ref="productFileidTwo"
            :file-list="logofileList"
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
        <el-form-item label="路径类型" prop="pathType">
          <el-radio v-model="dateFrom.pathType" label="0">否</el-radio>
          <el-radio v-model="dateFrom.pathType" label="1">商品</el-radio>
          <el-radio v-model="dateFrom.pathType" label="2">APP页面</el-radio>
          <el-radio v-model="dateFrom.pathType" label="3">外部地址</el-radio>
        </el-form-item>
        <el-form-item label="排序" prop="sortOrder">
          <el-input v-model="dateFrom.sortOrder" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" @click="onCancel">取 消</el-button>
        <el-button type="primary" @click="submitForm('addBanner')"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  bannerListApi,
  createApi,
  updateByIdApi,
  removeByIdApi,
  delImgApi,
} from "@/api/banner";

import { getToken } from "@/utils/auth";
export default {
  data() {
    return {
      size: 10,
      current: 1,
      total: 0,
      tableData: [],
      listuser: [],
      logofileList: [],
      headers: { token: getToken() },
      title: undefined,
      dialogFormVisible: false,
      dateFrom: {
        title: undefined, // 标题
        bannerUrl: undefined, // Banner地址
        pathType: undefined, // 路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址
        path: undefined, // 跳转路径
        sortOrder: undefined, // 数字越小 排序优先级越高 一级类目默认0
      },
      logoAction: process.env.VUE_APP_BASE_API + "/oss-do",
      imageUrl: "",
      rules: {
        title: [{ required: true, message: "输入不能为空", trigger: "blur" }],
        bannerUrl: [{ required: true, message: "请上传图片", trigger: "blur" }],
        pathType: [
          { required: true, message: "输入不能为空", trigger: "blur" },
        ],
      },
      pathType: "0",
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
    // 创建弹框
    addBanner() {
      this.dialogFormVisible = true;
    },
    //更新
    updateById(row) {
      this.dateFrom = {};
      // console.log(row);
      this.dateFrom = row;
      // console.log(this.form);
      this.dialogFormVisible = true;
      // this.form.name = name
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

    // 查询列表
    getlist() {
      bannerListApi({
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
      this.dateFrom.bannerUrl = res.result;
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
        } else {
          this.$message({
            message: res.message,
            type: res.success,
          });
        }
      });
    },

    // 创建
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.dateFrom.id) {
            createApi(this.dateFrom).then((res) => {
              if (res.code === 200) {
                this.$message({
                  message: res.message,
                  type: res.success,
                });
                this.getlist();
              }
            });
          } else {
            updateByIdApi(this.dateFrom).then((res) => {
              if (res.code === 200) {
                this.$message({
                  message: res.message,
                  type: res.success,
                });
                this.getlist();
              }
            });
          }
          this.logofileList = [];
          this.dateFrom = {
            title: undefined, // 标题
            bannerUrl: undefined, // Banner地址
            pathType: undefined, // 路径类型：0->默认不跳转；1->商品；2->APP页面；3->外部地址
            path: undefined, // 跳转路径
            sortOrder: undefined, // 数字越小 排序优先级越高 一级类目默认0
          };

          this.dialogFormVisible = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    // 修改
    create(row) {
      this.dateFrom = {};
      // console.log(row);
      this.dateFrom = row;
      // console.log(this.form);
      this.dialogFormVisible = true;
      // this.form.name = name
    },

    // 删除
    removeBanner(id) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(id);
          removeByIdApi(id).then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getlist();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
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
