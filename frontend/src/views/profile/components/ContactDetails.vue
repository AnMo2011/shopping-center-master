<template>
  <el-form>
    <el-form-item label="Email">
      <div class="user-profile">
        <div class="box-center">
          <!-- 网名 -->
          <div class="user-name text-center">ejzblog@163.com</div>
        </div>
      </div>
    </el-form-item>

    <el-form-item label="简书">
      <div class="user-profile">
        <div class="box-center">
          <!-- 网名 -->
          <div class="user-name text-center"></div>
        </div>
      </div>
    </el-form-item>

    <el-form-item label="掘金">
      <div class="user-profile">
        <div class="box-center">
          <!-- 网名 -->
          <div class="user-name text-center"></div>
        </div>
      </div>
    </el-form-item>

    <el-form-item label="简介">
      <div class="user-profile">
        <div class="box-center">
          <!-- 网名 -->
          <div class="user-name text-center"></div>
        </div>
      </div>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: "",
          email: "",
        };
      },
    },
  },
  methods: {
    submit() {
      this.$message({
        message: "User information has been updated successfully",
        type: "success",
        duration: 5 * 1000,
      });
    },
  },
};
</script>
