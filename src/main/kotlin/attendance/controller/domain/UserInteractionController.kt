package attendance.controller.domain

import attendance.model.Student
import attendance.util.getDay
import attendance.util.getToday
import attendance.util.standardFormatter
import attendance.view.InputView
import attendance.view.OutputView
import java.time.LocalDate
import java.time.LocalDateTime

class UserInteractionController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun handleStartMsg(): String {
        val day = getDay()
        outputView.showMsg("오늘은 12월 ${day}일 기능을 선택해 주세요.")
        outputView.showMsg("1. 출석 확인")
        outputView.showMsg("2. 출석 수정")
        outputView.showMsg("3. 크루별 출석 기록 확인")
        outputView.showMsg("4. 제적 위험자 확인")
        outputView.showMsg("Q. 종료")
        return inputView.getInput()
    }

    fun handleNickName(): String {
        outputView.showMsg("닉네임을 입력해 주세요.")
        return inputView.getInput()
    }

    fun handleEditNickName(): String {
        outputView.showMsg("출석을 수정하려는 크루의 닉네임을 입력해 주세요.")
        return inputView.getInput()
    }

    fun handleEditDay(): String {
        outputView.showMsg("수정하려는 날짜(일)를 입력해 주세요.")
        return inputView.getInput()
    }

    fun handleEditTime(): String {
        outputView.showMsg("언제로 변경하겠습니까?")
        return inputView.getInput()
    }

    fun handleEditAttendance(
        beforeTime: LocalDateTime,
        beforeStatus: String,
        AfterTime: LocalDateTime,
        AfterStatus: String
    ) {
        outputView.showMsg("언제로 변경하겠습니까?")
        val bTime = beforeTime.standardFormatter()
        val aTime = AfterTime.standardFormatter()
        outputView.showMsg("${bTime} (${beforeStatus}) -> ${aTime} (${AfterStatus}) 수정 완료!")
    }

    fun handleAttendanceTime(): String {
        outputView.showMsg("등교 시간을 입력해 주세요.")
        return inputView.getInput()
    }

    fun handleAttendTime(time: LocalDateTime, status: String) {
        val checkTime = time.standardFormatter()
        outputView.showMsg("${checkTime} (${status})")
    }

    fun handleStatus(student: Student) {
        outputView.showMsg("\n출석: ${student.attendanceCount}회\n지각: ${student.lateCount}회\n결석: ${student.absenceCount}회\n\n${student.status.status} 대상자입니다.")
    }

    fun handleDangerPeople(dangerStudents: List<Student>) {
        outputView.showMsg("제적 위험자 조회 결과")
        for (student in dangerStudents) {
            outputView.showMsg("- ${student.name}: 결석 ${student.absenceCount}회, 지각 ${student.lateCount}회 (${student.status.status})")
        }
    }
}