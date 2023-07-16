package com.example.thischatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(val context: Context, val messageList:ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1;
    val ITEM_SEND =2;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType==1){
            //inflate received
            val view: View = LayoutInflater.from(context).inflate(R.layout.received,parent,false)
            return receivedViewHolder(view)
        }else{
            //inflate
            val view: View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return sendViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]
        if(holder.javaClass == sendViewHolder::class.java){

            //stuff for send view holder

            val viewHolder = holder as sendViewHolder
            holder.sendMessage.text = currentMessage.message

        }else{
            //stuff for receive view holder
            val viewHolder = holder as receivedViewHolder
            holder.receivedMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SEND
        }else{
            return ITEM_RECEIVED
        }
    }

    class sendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val sendMessage = itemView.findViewById<TextView>(R.id.text_send_message)

    }


    class receivedViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val receivedMessage = itemView.findViewById<TextView>(R.id.text_received_message)

    }
}