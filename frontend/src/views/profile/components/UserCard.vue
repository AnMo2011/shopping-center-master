<template>
  <el-card style="margin-bottom: 20px">
    <!-- 关于我 -->
    <div slot="header" class="clearfix">
      <span>About me</span>
    </div>

    <div class="user-profile">
      <!-- 头像 -->
      <div class="box-center">
        <div
          data-v-799537af
          data-v-14daffcb
          class="pan-item"
          style="z-index: 1; height: 100px; width: 100px"
        >
          <div data-v-799537af class="pan-info">
            <span class="title">{{ fit }}</span>
            <el-avatar shape="square" :size="100" :fit="fit" :src="url">
            </el-avatar>
          </div>
        </div>
      </div>

      <div class="box-center">
        <!-- 网名 -->
        <div class="user-name text-center">码农老中医</div>
        <!-- 工作 -->
        <div class="user-role text-center text-muted">Java开发工程师</div>
      </div>
    </div>

    <div class="user-bio">
      <div class="user-skills user-bio-section">
        <div class="user-bio-section-header">
          <svg-icon icon-class="skill" /><span>Skills</span>
        </div>
        <div class="user-bio-section-body">
          <!-- Java基础 -->
          <div class="progress-item">
            <span>Java基础</span>
            <el-progress :percentage="100" status="success" />
          </div>
          <div
            data-v-799537af
            data-v-14daffcb
            class="pan-item"
            style="z-index: 1; height: 15px; width: 100px"
          ></div>

          <!-- Web框架与源码 -->
          <div class="progress-item">
            <span>Web框架与源码</span>
            <el-progress :percentage="50" />
          </div>
          <div
            data-v-799537af
            data-v-14daffcb
            class="pan-item"
            style="z-index: 1; height: 15px; width: 100px"
          ></div>

          <!-- 数据库 -->
          <div class="progress-item">
            <span>数据库</span>
            <el-progress :percentage="80" />
          </div>
          <div
            data-v-799537af
            data-v-14daffcb
            class="pan-item"
            style="z-index: 1; height: 15px; width: 100px"
          ></div>

          <!-- Linux -->
          <div class="progress-item">
            <span>Linux</span>
            <el-progress :percentage="50" />
          </div>
          <div
            data-v-799537af
            data-v-14daffcb
            class="pan-item"
            style="z-index: 1; height: 15px; width: 100px"
          ></div>

          <!-- 消息中间件 -->
          <div class="progress-item">
            <span>消息中间件</span>
            <el-progress :percentage="30" />
          </div>
          <div
            data-v-799537af
            data-v-14daffcb
            class="pan-item"
            style="z-index: 1; height: 15px; width: 100px"
          ></div>

          <!-- 数据结构与算法 -->
          <div class="progress-item">
            <span>数据结构与算法</span>
            <el-progress :percentage="10" />
          </div>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>
import PanThumb from "@/components/PanThumb";

export default {
  data() {
    return {
      fits: ["fill", "contain", "cover", "none", "scale-down"],
      url: "https://shopping-center-master.oss-cn-hangzhou.aliyuncs.com/1657355473809.jpg",
    };
  },
};
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }
}

.user-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 14px;
    padding: 15px 0;

    .user-bio-section-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}

.user-profile .user-name[data-v-14daffcb] {
  font-weight: 700;
}

.text-center {
  text-align: center;
}
</style>
