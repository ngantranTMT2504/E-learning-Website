import StudentLayout from "@/layouts/StudentLayout.vue";
import TeacherLayout from "@/layouts/TeacherLayout.vue";
import TeacherCourseList from "@/views/Teacher/TeacherCourseManagement/TeacherCourseList.vue";
import TeacherDashboardView from "@/views/Teacher/TeacherDashboardView.vue";
import AuthLayout from "@/layouts/AuthLayout.vue";
import LoginView from "@/views/Auth/LoginView.vue";
import OtpConfirmView from "@/views/Auth/OtpConfirmView.vue";
import RegisterView from "@/views/Auth/RegisterView.vue";
import HomeStudentView from "@/views/Student/HomeStudentView.vue";
import CourseDetailView from "@/views/Student/CourseDetailView.vue";
import { createRouter, createWebHistory } from "vue-router";
import CourseStudentView from "@/views/Student/CourseStudentView.vue";
import ContactView from "@/views/Student/ContactView.vue";
import PaymentView from "@/views/Student/PaymentView.vue";
import VerifyTeacherView from "@/views/Auth/VerifyTeacherView.vue";
import TeacherCourseFormCreate from "@/views/Teacher/TeacherCourseManagement/TeacherCourseFormCreate.vue";
import TeacherLessonList from "@/views/Teacher/TeacherLessonManagement/TeacherLessonList.vue";
import TeacherBalance from "@/views/Teacher/TeacherBalance/TeacherBalance.vue";
import TeacherInformation from "@/views/Teacher/TeacherInfomartion/TeacherInformation.vue";
import TeacherBankAccount from "@/views/Teacher/TeacherInfomartion/TeacherBankAccount.vue";
import TeacherWithdraw from "@/views/Teacher/TeacherBalance/TeacherWithdraw.vue";
import TeacherWithdrawOtpConfirm from "@/views/Teacher/TeacherBalance/TeacherWithdrawOtpConfirm.vue";
import TeacherLessonCreate from "@/views/Teacher/TeacherLessonManagement/TeacherLessonCreate.vue";
import TeacherCourseStatistical from "@/views/Teacher/TeacherStatistical/TeacherCourseStatistical.vue";
import TeacherCourseUpdate from "@/views/Teacher/TeacherCourseManagement/TeacherCourseUpdate.vue";
import TeacherLessonUpdate from "@/views/Teacher/TeacherLessonManagement/TeacherLessonUpdate.vue";
import TeacherGear from "@/views/Teacher/TeacherGear.vue";
import TeacherCredentialList from "@/views/Teacher/TeacherCredential/TeacherCredentialList.vue";
import LessonView from "@/views/Student/LessonView.vue";
import AdminDashboard from "@/views/Admin/AdminDashboard.vue";
import AdminLayout from "@/layouts/AdminLayout.vue";
import AdminManagementAccount from "@/views/Admin/AdminManagementAccount/AdminManagementAccount.vue";
import AdminTeacherCertificateList from "@/views/Admin/TeacherCertificateManager/AdminCertificateList.vue";
import AdminTeacherCertificateCreate from "@/views/Admin/TeacherCertificateManager/AdminTeacherCertificateCreate.vue";

import AdminTypeCourseList from "@/views/Admin/AdminTypeCourse/AdminTypeCourseList.vue";
import AdminTypeCourseUpdate from "@/views/Admin/AdminTypeCourse/AdminTypeCourseUpdate.vue";
import AdminCommission from "@/views/Admin/AdminCommission/AdminCommissionList.vue";

import MonthlyRevenueStatistics from "@/views/Admin/AdminStatistical/MonthlyRevenueStatistics.vue";
import AdminInformation from "@/views/Admin/AdminInformation/AdminInformation.vue";
import AdminGear from "@/views/Admin/AdminGear.vue";
import BankInfoView from "@/views/Auth/BankInfoView.vue";
import TeacherCourseCreate from "@/views/Teacher/TeacherCourseManagement/TeacherCourseCreate.vue";

const router = createRouter(
  {
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
      //auth
      {
        path: "/auth",
        component: AuthLayout,
        children: [
          { path: "login", name: "auth-login", component: LoginView },
          { path: "register", name: "auth-register", component: RegisterView },
          { path: "otp-confirm", name: "auth-otp-confirm", component: OtpConfirmView},
          { path: "verify-teacher", name: "verify teacher", component: VerifyTeacherView},
          { path: "bank-info", name: "bank info", component: BankInfoView},
        ],
      },
      //client
      {
        path: "/",
        component: StudentLayout,
        children: [
          { path: "", name: "home", component: HomeStudentView },
          { path: "course", name: "course", component: CourseStudentView },
          { path: "course-detail/:slug", name: "course-detail", component: CourseDetailView},
          { path: "contact", name: "contact", component: ContactView },
          { path: "payment/:slug", name: "payment", component: PaymentView },
          { path: "lesson/:slug/:id", name: "lesson", component: LessonView}
        ],
      },

  //teacher
      {
        path: "/teacher",
        component: TeacherLayout,
        redirect: "/teacher/dashboard",
        // meta: { requiresTeacher: true , role: ['Teacher']}, // Chỉ teacher mới truy cập được
        children: [
          { path: "dashboard", name: "teacher-dashboard", component: TeacherDashboardView},
          { path: "gear", name: "teacher-gear", component: TeacherGear},
          { path: "credentials", name: "teacher-credentials", component: TeacherCredentialList},
          { path: "balance", name: "teacher-balance", component: TeacherBalance },
          { path: "withdraw", name: "teacher-withdraw", component: TeacherWithdraw },
          { path: "withdraw-otp", name: "teacher-withdrawOtp", component: TeacherWithdrawOtpConfirm },
          { path: "info", name: "teacher-info", component: TeacherInformation },
          { path: "bank", name: "teacher-bank", component: TeacherBankAccount },
          { path: "course",
            children: [
              { path: "list", name: "teacher-course-list", component: TeacherCourseList},
              { path: "create", name: "teacher-course-create", component: TeacherCourseCreate},
              { path: 'update/:id', name: 'teacher-course-update', component: TeacherCourseUpdate },
            ]
          },
          { path: "lesson",
            children: [
              { path: "list", name: "teacher-lesson-list", component: TeacherLessonList},
              { path: 'create', name: 'teacher-lesson-create', component: TeacherLessonCreate },
              { path: 'update/:id', name: 'teacher-lesson-update', component: TeacherLessonUpdate },
            ]
          },
          { path: "statistical",
            children: [
              { path: "course", name: "teacher-statistical-course",component: TeacherCourseStatistical},
              // { path: 'update/:id', name: 'teacher-course-update', component: TeacherCourseUpdate },
            ],
          },
        ],
      },
       //admin
      {
        path: "/admin",
        component: AdminLayout,
        redirect: "/admin/dashboard",
        // meta: { requiresAdmin: true , role: ['Admin']}, 
        children: [
          { path: "dashboard", name: "admin-dashboard", component: AdminDashboard},
          { path: "account-management", name: "account-management", component: AdminManagementAccount},
          { 
            path: "type-course", 
            children: [
              { path: "list", name: "type-course-list", component: AdminTypeCourseList},
              { path: 'update/:id', name: 'type-course-update', component: AdminTypeCourseUpdate },
            ]
          },
          { 
            path: "commission", 
            children: [
              { path: "list", name: "commission-list", component: AdminCommission},
              { path: 'update/:id', name: 'commission-update', component: AdminTypeCourseUpdate },
            ]
          },
          { 
            path: "teacher-certificate", 
            children: [
              { path: "list", name: "teacherCertificate-list", component: AdminTeacherCertificateList},
              { path: 'create', name: 'teacherCertificate-create', component: AdminTeacherCertificateCreate },
              { path: 'update/:id', name: 'teacherCertificate-update', component: AdminTypeCourseUpdate },
            ]
          },
          { 
            path: "statistical", 
            children: [
              { path: "monthly-revennue-statistical", name: "monthly-revennue-statistical", component: MonthlyRevenueStatistics },
              
            ]
          },
          { path: "info", name: "admin-info", component: AdminInformation},
          { path: "gear", name: "admin-gear", component: AdminGear},
          { path: "account-management", name: "admin-account-management", component: AdminManagementAccount},
        ]
      },
    ]
  }
);

export default router;
