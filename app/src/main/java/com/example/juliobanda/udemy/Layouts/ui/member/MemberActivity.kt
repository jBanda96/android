/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.juliobanda.udemy.Layouts.ui.member

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.juliobanda.udemy.Layouts.Member
import com.example.juliobanda.udemy.Layouts.repository.remote.RemoteRepository
import com.example.juliobanda.udemy.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_member.*
import kotlinx.android.synthetic.main.activity_team_members.*


class MemberActivity : AppCompatActivity(), MemberContract.View {

  private lateinit var presenter: MemberContract.Presenter

  companion object {
    const val EXTRA_MEMBER_LOGIN = "EXTRA_MEMBER_LOGIN"

    fun newIntent(context: Context, memberLogin: String): Intent {
      val intent = Intent(context, MemberActivity::class.java)
      intent.putExtra(EXTRA_MEMBER_LOGIN, memberLogin)
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_member)

    setupPresenter()
    setupActionBar()

    presenter.retrieveMember(memberLoginFromIntent())
  }

  private fun setupPresenter() {
    presenter = MemberPresenter(RemoteRepository(), this)
  }

  private fun setupActionBar() {
    title = memberLoginFromIntent()
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun memberLoginFromIntent() = intent.getStringExtra(EXTRA_MEMBER_LOGIN)

  override fun showMember(member: Member) {
    Picasso.get().load(member.avatarUrl).into(memberAvatar)

    memberName.text = member.name
    memberLogin.text = member.login
    memberCompany.text = member.company
    memberEmail.text = member.email
    memberType.text = member.type
  }

  override fun showErrorRetrievingMember() {
    Toast.makeText(this, getString(R.string.error_retrieving_member), Toast.LENGTH_SHORT).show()
  }

  override fun hideName() {
    memberName.visibility = View.GONE
  }

  override fun hideEmail() {
    memberEmailContainer.visibility = View.GONE
  }

  override fun hideCompany() {
    memberCompanyContainer.visibility = View.GONE
  }
}
