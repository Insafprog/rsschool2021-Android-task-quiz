package com.rsschool.quiz

import android.app.Activity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionFragment: Fragment() {

    private val model: QuestionViewModel by activityViewModels()

    private var _binding: FragmentQuizBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contextThemeWrapper = when(model.getIndex) {
            1 -> ContextThemeWrapper(activity, R.style.Theme_Quiz_Second)
            2 -> ContextThemeWrapper(activity, R.style.Theme_Quiz_Three)
            3 -> ContextThemeWrapper(activity, R.style.Theme_Quiz_Four)
            4 -> ContextThemeWrapper(activity, R.style.Theme_Quiz_Five)
            else -> ContextThemeWrapper(activity, R.style.Theme_Quiz_First)
        }
        val lInflater = inflater.cloneInContext(contextThemeWrapper)
        _binding = FragmentQuizBinding.inflate(lInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var index = model.getIndex

        binding.previousButton.isEnabled = index > 0
        binding.nextButton.isEnabled = model.question.thisAnswer != null
        binding.nextButton.text = if (index == model.questionsCount - 1) "Submit" else "Next"
        if (index == 0){
            binding.toolbar.navigationIcon = null
            binding.toolbar.title = "           Question 1"
        }
        else binding.toolbar.title = "Question ${index + 1}"

        with(model.question) {
            binding.question.text = quest
            binding.optionOne.text = option0
            binding.optionTwo.text = option1
            binding.optionThree.text = option2
            binding.optionFour.text = option3
            binding.optionFive.text = option4
            thisAnswer?.let{
                when(it) {
                    0 -> binding.optionOne.isChecked = true
                    1 -> binding.optionTwo.isChecked = true
                    2 -> binding.optionThree.isChecked = true
                    3 -> binding.optionFour.isChecked = true
                    4 -> binding.optionFive.isChecked = true
                }
            }
        }

        binding.previousButton.setOnClickListener{
            index--
            model.setIndex(index)
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragmentContainerView, QuestionFragment(), index.toString())
            }
        }

        if (index != 0) binding.toolbar.setNavigationOnClickListener {
            index--
            model.setIndex(index)
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragmentContainerView, QuestionFragment(), index.toString())
            }
        }

        binding.nextButton.setOnClickListener{
            if (index == model.questionsCount - 1)
                activity?.supportFragmentManager?.commit {
                    replace(R.id.fragmentContainerView, QuestionFragment(), index.toString())
                }
            else {
                index++
                model.setIndex(index)
                activity?.supportFragmentManager?.commit {
                    replace(R.id.fragmentContainerView, QuestionFragment(), index.toString())
                }
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            binding.nextButton.isEnabled = true
            val qu = model.question
            when(i){
                R.id.option_one -> qu.thisAnswer = 0
                R.id.option_two -> qu.thisAnswer = 1
                R.id.option_three -> qu.thisAnswer = 2
                R.id.option_four -> qu.thisAnswer = 3
                R.id.option_five -> qu.thisAnswer = 4
            }
            model.setQuestion(qu)
        }

    }
}