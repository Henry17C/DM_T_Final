package com.dm.coyago.zentunes.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.databinding.SongItemsBinding
import com.dm.coyago.zentunes.logic.local.Song


class MusicAdapterDiffUtil( private val onSelectItem:(Song)->Unit):ListAdapter<Song, MusicAdapterDiffUtil.ViewHolderMusic>(DiffUtilSongCallback) {

    class ViewHolderMusic(view: View) : RecyclerView.ViewHolder(view){

        private var binding: SongItemsBinding= SongItemsBinding.bind(view)

        fun render(item: Song, onSelectItem:(Song)->Unit
         ){



            try {
                binding.imageView.load(item.albumArtUri.toString()) {
                    crossfade(true)
                    placeholder(R.drawable.image)
                    transformations(CircleCropTransformation())
                }
            } catch (e: Exception) {
                Log.e("ImageLoading", "Error al cargar la imagen: ${e.message}")
            }
            binding.txtArtist.text=item.artist.toString()
            binding.txtTitle.text=item.title.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMusic {
        val inflater= LayoutInflater.from(parent.context)
        return ViewHolderMusic(inflater.inflate(R.layout.song_items,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolderMusic, position: Int) {
        holder.render(getItem(position), onSelectItem)
        val song = getItem(position)
       // holder.render(song)

        // Configura el OnClickListener para el elemento actual
        holder.itemView.setOnClickListener {
            onSelectItem(song)
        }
    }


}

private object DiffUtilSongCallback : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return (oldItem==newItem)
    }


}