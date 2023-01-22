package com.android.erwin.challenge4gamesuit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import com.android.erwin.challenge4gamesuit.databinding.ActivityMainBinding
import com.android.erwin.challenge4gamesuit.databinding.DialogGameSuitBinding
import com.android.erwin.challenge4gamesuit.model.Computer
import com.android.erwin.challenge4gamesuit.model.Player

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingDialogGameSuitBinding: DialogGameSuitBinding
    private lateinit var player: Player
    private lateinit var computer: Computer
    private var isFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        computer = Computer()
        player = Player()
        startGame()
    }

    override fun startGame() {
        initChoice()
        setImageChoice()
        initialState()
        setOnclickListeners()
    }

    private fun initChoice() {
        player.choice = arrayListOf(
            binding.imgBatuPlayer,
            binding.imgKertasPlayer,
            binding.imgGuntingPlayer
        )

        computer.choice = arrayListOf(
            binding.imgBatuCom,
            binding.imgKertasCom,
            binding.imgGuntingCom
        )
    }

    private fun setImageChoice() {
        player.choice[SuitService.BATU.index].setImageResource(R.drawable.batu)
        player.choice[SuitService.KERTAS.index].setImageResource(R.drawable.kertas)
        player.choice[SuitService.GUNTING.index].setImageResource(R.drawable.gunting)

        computer.choice[SuitService.BATU.index].setImageResource(R.drawable.batu)
        computer.choice[SuitService.KERTAS.index].setImageResource(R.drawable.kertas)
        computer.choice[SuitService.GUNTING.index].setImageResource(R.drawable.gunting)
    }

    private fun initialState() {
        (player.choice + computer.choice).forEach { it.background = null }
        binding.textView5.text = getString(R.string.vs)
        isFinished = false
    }


    private fun setOnclickListeners() {
        player.choice.forEachIndexed { index, image ->
            image.setOnClickListener {
                if (!isFinished) {
                    player.player = index
                    it.background =
                        ContextCompat.getDrawable(this, R.drawable.background_selected)
                    computer.player = (0..2).random()
                    computer.choice[computer.player].background =
                        ContextCompat.getDrawable(this, R.drawable.background_selected)
                    winnerGame()
                    isFinished = true
                }
            }
        }

        binding.imgRefresh.setOnClickListener {
            initialState()
        }
    }

    override fun winnerGame() {

        bindingDialogGameSuitBinding = DialogGameSuitBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(bindingDialogGameSuitBinding.root)

        if ( (player.player + 1) % 3 == computer.player) {
            bindingDialogGameSuitBinding.textView9.text = getString(R.string.com_n_menang)
            bindingDialogGameSuitBinding.textView9.setTextColor(Color.GREEN)
        }else if(player.player == computer.player){
            bindingDialogGameSuitBinding.textView9.text = getString(R.string.draw)
            bindingDialogGameSuitBinding.textView9.setTextColor(Color.BLUE)
        }else{
            bindingDialogGameSuitBinding.textView9.text = getString(R.string.pemain_1_nmenang)
            bindingDialogGameSuitBinding.textView9.setTextColor(Color.GREEN)
        }

        dialog.show()

    }
}