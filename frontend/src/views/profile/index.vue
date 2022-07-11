<template>
  <div class="main">
    <div class="app-container">
      <div v-if="user">
        <el-row :gutter="20">
          <el-col :span="6" :xs="24">
            <user-card :user="user" />
          </el-col>

          <el-col :span="18" :xs="24">
            <el-card>
              <el-tabs v-model="activeTab">
                <el-tab-pane label="ContactDetails" name="contactDetails">
                  <contactDetails />
                </el-tab-pane>
                <el-tab-pane label="WorkTime" name="workTime">
                  <workTime />
                </el-tab-pane>
              </el-tabs>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import UserCard from "./components/UserCard";
import ContactDetails from "./components/ContactDetails";
import WorkTime from "./components/WorkTime";

export default {
  name: "Profile",
  components: { UserCard, ContactDetails, WorkTime },
  data() {
    return {
      user: {},
      activeTab: "contactDetails",
    };
  },
};
</script>

<style scoped>
.main {
  padding: 30px;
  background-color: #f5f5f5;
}
</style>
