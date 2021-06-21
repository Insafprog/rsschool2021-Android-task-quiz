package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.rsschool.quiz.databinding.FragmentSubmitBinding
import kotlin.system.exitProcess

class SubmitFragment : Fragment() {

    private val model: QuestionViewModel by activityViewModels()

    private var _binding: FragmentSubmitBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSubmitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resultView.text = "Your result: ${model.result} out of ${model.questionsCount}"

        binding.shareButton.setOnClickListener {
            val resultIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, model.toString())
                putExtra(Intent.EXTRA_SUBJECT, "Quiz results")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(resultIntent, null)
            startActivity(shareIntent)
        }

        binding.backButton.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragmentContainerView, QuestionFragment())
            }
        }

        binding.closeButton.setOnClickListener {
            activity?.finishAffinity()
            exitProcess(0)
        }
    }
}