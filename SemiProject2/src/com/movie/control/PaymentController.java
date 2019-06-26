package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.movie.view.PasswordCheckView;
import com.movie.view.PaymentView;

public class PaymentController {

	PaymentView payView;
	PasswordCheckView passView;

	int pass = 1234;// 임시비밀번호

	public PaymentController() {
		payView = new PaymentView();
		passView = new PasswordCheckView();

		eventUp();
		
		
	}

	private void eventUp() {
		payView.bt_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		payView.bt_prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	
		//결제버튼 클릭
		payView.btPayment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password = payView.showInput("비밀번호를 다시 한 번 입력해주세요");
				if(Integer.parseInt(password)!=pass) {
					payView.showMsg("비밀번호가 틀렸습니다");
					return;
				}
				if(payView.showConfirm()!=0) {
					return;
				}
				payView.showMsg("결제가 완료되었습니다.\n감사합니다.");
			}
		});
		
		
	}

	public static void main(String[] args) {
		new PaymentController();
	}

}
